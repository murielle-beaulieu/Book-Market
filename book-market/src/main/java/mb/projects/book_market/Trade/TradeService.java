package mb.projects.book_market.Trade;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import mb.projects.book_market.Book.Book;
import mb.projects.book_market.Book.BookRepository;
import mb.projects.book_market.Book.BookService;
import mb.projects.book_market.EmailConfig.EmailService;
import mb.projects.book_market.Enums.TradeStatus;
import mb.projects.book_market.User.User;
import mb.projects.book_market.User.UserRepository;

@Service
public class TradeService {

    private final TradeRepository tradeRepo;
    private final UserRepository userRepo;
    private final BookRepository bookRepo;
    private final BookService bookService;
    private final ModelMapper mapper;
    private EmailService emailService;

    public TradeService(TradeRepository tradeRepo, UserRepository userRepo, BookRepository bookRepo,
            ModelMapper mapper, BookService bookService, EmailService emailService) {
        this.tradeRepo = tradeRepo;
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
        this.bookService = bookService;
        this.mapper = mapper;
        this.emailService = emailService;
    }

    public List<Trade> getAllTrades() {
        return tradeRepo.findAll();
    }

    public Trade getTradeById(Long id) {
        Optional<Trade> found = tradeRepo.findById(id);
        if (found.isEmpty()) {
            return null;
        }
        Trade result = found.get();
        return result;
    }

    public Trade createTrade(TradeDTO tradeData) throws Exception {
        User userOffering = userRepo.findById(tradeData.getUserOffering_id()).get();
        User userReceiving = userRepo.findById(tradeData.getUserReceiving_id()).get();
        Book bookOffered = bookRepo.findById(tradeData.getBookOffered_id()).get();
        Book bookReceived = bookRepo.findById(tradeData.getBookRequested_id()).get();

        Trade newTrade = mapper.map(tradeData, Trade.class);

        newTrade.setBookOffered(bookOffered);
        newTrade.setBookRequested(bookReceived);
        newTrade.setUserOffering(userOffering);
        newTrade.setUserReceiving(userReceiving);

        emailService.newTradeInitiated(userOffering.getFirstName(), userOffering.getEmail(),
                userReceiving.getFirstName(), userReceiving.getEmail(),
                bookOffered.getTitle(), bookReceived.getTitle());

        tradeRepo.save(newTrade);
        return newTrade;
    }

    public Trade updateTrade(Long id, UpdateTradeDTO tradeData) {
        Optional<Trade> found = tradeRepo.findById(id);
        if (found.isEmpty()) {
            return null;
        }
        Trade result = found.get();
        mapper.map(tradeData, result);
        tradeRepo.save(result);
        return result;
    }

    public void cancelTrade(Long id) {
        Trade found = tradeRepo.findById(id).get();
        found.setIsCancelled(Boolean.TRUE);
    }

    public Trade approveTrade(Long id) throws MessagingException {

        // What do we want to happen when we approve a trade?
        Trade trade = tradeRepo.findById(id).get();
        User userOffering = trade.getUserOffering();
        User userReceiving = trade.getUserReceiving();
        Book bookOffered = trade.getBookOffered();
        Book bookReceived = trade.getBookRequested();

        System.out.println("user offering: " + userOffering);
        System.out.println("user receiving: " + userReceiving);
        // - We change the trade status to Approved
        trade.setTradeStatus(TradeStatus.ACCEPTED);

        // - User offering "gives" a book (this is the book we mark as traded)

        // We notify the users via email
        emailService.tradeUpdateMessage(userOffering.getFirstName(), userOffering.getEmail(),
                userReceiving.getFirstName(), userReceiving.getEmail(),
                bookOffered.getTitle(), bookReceived.getTitle(), "Approved");

        // Here the swap happens: userReceiving gets the bookOffered
        // and userOffering gets the bookRequested
        bookService.tradeBook(trade.getBookOffered(), userReceiving.getId());
        bookService.tradeBook(trade.getBookRequested(), userOffering.getId());
        tradeRepo.save(trade);
        return trade;

        // - User receiving the offer also "gives" a book (we also mark as traded)

        // - Each user "gains" a new book (we copy to the user inventory) ?
    }

    public void declineTrade(Long id) throws MessagingException {
        Trade trade = tradeRepo.findById(id).get();
        User userOffering = trade.getUserOffering();
        User userReceiving = trade.getUserReceiving();
        Book bookOffered = trade.getBookOffered();
        Book bookReceived = trade.getBookRequested();

        trade.setTradeStatus(TradeStatus.DENIED);
        tradeRepo.save(trade);

        emailService.tradeUpdateMessage(userOffering.getFirstName(), userOffering.getEmail(),
                userReceiving.getFirstName(), userReceiving.getEmail(),
                bookOffered.getTitle(), bookReceived.getTitle(), "Declined");
    }

    public List<Trade> getAllTradesByUser(Long id) {
        Optional<User> found = this.userRepo.findById(id);
        if (found.isEmpty()) {
            return null;
        }
        User result = found.get();
        List<Trade> allTrades = getAllTrades();
        return allTrades.stream().filter(trade ->  trade.getUserOffering().equals(result) || trade.getUserReceiving().equals(result))
        .collect(Collectors.toList());
    }

    public List<Trade> getTradesByUserAndTradeType(Long id, String tradeType) {
        Optional<User> found = this.userRepo.findById(id);
        if (found.isEmpty()) {
            return null;
        }
        User result = found.get();

        List<Trade> allTrades = getAllTrades();

        List<Trade> tradesFiltered = new ArrayList<Trade>();

        if (tradeType.equalsIgnoreCase("offering")) {
            tradesFiltered = allTrades.stream().filter(trade -> trade.getUserOffering().equals(result))
                    .collect(Collectors.toList());
        }

        if (tradeType.equalsIgnoreCase("receiving")) {
            tradesFiltered = allTrades.stream().filter(trade -> trade.getUserReceiving().equals(result))
                    .collect(Collectors.toList());
        }

        return tradesFiltered;
    }

    public List<Trade> getTradesByStatus(String status) {
        List<Trade> allTrades = getAllTrades();
        return allTrades.stream().filter(trade -> trade.getTradeStatus().getDisplayName().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

}
