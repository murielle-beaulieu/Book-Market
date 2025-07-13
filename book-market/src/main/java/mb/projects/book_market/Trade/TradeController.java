package mb.projects.book_market.Trade;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trades")
public class TradeController {

    private final TradeService service;

    public TradeController(TradeService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Trade>> getAllTrades() {
        List<Trade> allTrades = service.getAllTrades();
        return new ResponseEntity<>(allTrades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trade> getTradeById(@PathVariable Long id) {
        Trade result = service.getTradeById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Trade> createTrade(@RequestBody TradeDTO tradeData) throws Exception {
        Trade newTrade = service.createTrade(tradeData);
        return new ResponseEntity<>(newTrade, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Trade> updateTrade(@PathVariable Long id, @RequestBody UpdateTradeDTO tradeData) {
        Trade updatedTrade = service.updateTrade(id, tradeData);
        return new ResponseEntity<>(updatedTrade, HttpStatus.OK);
    }
    
    @PatchMapping("/approve/{id}")
    public void approveTrade(@PathVariable Long id) {
        service.approveTrade(id);
    }

    @DeleteMapping("/{id}")
    public HttpStatus cancelTrade(@PathVariable Long id) {
        service.cancelTrade(id);
        return HttpStatus.OK;
    }

}
