package mb.projects.book_market.Trade;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import mb.projects.book_market.Book.Book;
import mb.projects.book_market.Book.BookRepository;
import mb.projects.book_market.User.User;
import mb.projects.book_market.User.UserRepository;

@Service
public class TradeServices {

    private final TradeRepository tradeRepo;
    private final UserRepository userRepo;
    private final BookRepository bookRepo;
    private final ModelMapper mapper;

    public TradeServices(TradeRepository tradeRepo, UserRepository userRepo, BookRepository bookRepo,
            ModelMapper mapper) {
        this.tradeRepo = tradeRepo;
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
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
        User userOffering = userRepo.findById(tradeData.getUserOffering_id()).orElseThrow(() -> new Exception("No User"));
        User userReceiving = userRepo.findById(tradeData.getUserOffering_id()).orElseThrow(() -> new Exception("No User"));
        Book bookOffered = bookRepo.findById(tradeData.getUserOffering_id()).orElseThrow(() -> new Exception("No Book"));
        Book bookReceived= bookRepo.findById(tradeData.getUserOffering_id()).orElseThrow(() -> new Exception("No Book"));

        Trade newTrade = mapper.map(tradeData, Trade.class);

        // System.out.println("All data: userOffering: " + userOffering + " userReceiving: " + userReceiving + " bookOffered: " + bookOffered + " bookReceived: " + bookReceived);

        newTrade.setBookOffered(bookOffered);
        newTrade.setBookRequested(bookReceived);
        newTrade.setUserOffering(userOffering);
        newTrade.setUserReceiving(userReceiving);

        tradeRepo.save(newTrade);
        return newTrade;

    }

    public Trade updateTrade(UpdateTradeDTO tradeData, Long id) {
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
        found.setCancelled(true);
    }

}
