package mb.projects.book_market.Trade;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import mb.projects.book_market.Book.Book;
import mb.projects.book_market.Book.BookRepository;
import mb.projects.book_market.Book.BookService;
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

    public TradeService(TradeRepository tradeRepo, UserRepository userRepo, BookRepository bookRepo,
            ModelMapper mapper, BookService bookService) {
        this.tradeRepo = tradeRepo;
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
        this.bookService = bookService;
        this.mapper = mapper;
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
        Book bookReceived= bookRepo.findById(tradeData.getBookRequested_id()).get();

        Trade newTrade = mapper.map(tradeData, Trade.class);

        newTrade.setBookOffered(bookOffered);
        newTrade.setBookRequested(bookReceived);
        newTrade.setUserOffering(userOffering);
        newTrade.setUserReceiving(userReceiving);

        // System.out.println("All data: userOffering: " + userOffering.getId() + " userReceiving: " + userReceiving.getId() + " bookOffered: " + bookOffered.getId() + " bookReceived: " + bookReceived.getId());
        
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

    public Trade approveTrade(Long id) {

        // What do we want to happen when we approve a trade?
        Trade trade = tradeRepo.findById(id).get();
        User userOffering = trade.getUserOffering();
        User userReceiving = trade.getUserReceiving();

        System.out.println("user offering: " + userOffering);
        System.out.println("user receiving: " + userReceiving);
        // - We change the trade status to Approved
        trade.setTradeStatus(TradeStatus.ACCEPTED);

        // - User offering "gives" a book (this is the book we mark as traded)

        // Here the swap happens: userReceiving gets the bookOffered
        // and userOffering gets the bookRequested
        bookService.tradeBook(trade.getBookOffered(), userReceiving.getId());
        bookService.tradeBook(trade.getBookRequested(), userOffering.getId());
        tradeRepo.save(trade);
        return trade;

        // - User receiving the offer also "gives" a book (we also mark as traded)

        // - Each user "gains" a new book (we copy to the user inventory) ?
    }

}
