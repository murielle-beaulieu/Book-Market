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

import jakarta.mail.MessagingException;
import mb.projects.book_market.Enums.Role;
import mb.projects.book_market.Role.AllowedRoles;

@RestController
@RequestMapping("/trades")
public class TradeController {

    private final TradeService service;

    public TradeController(TradeService service) {
        this.service = service;
    }

    @AllowedRoles({ Role.ADMIN })
    @GetMapping()
    public ResponseEntity<List<Trade>> getAllTrades() {
        List<Trade> allTrades = service.getAllTrades();
        return new ResponseEntity<>(allTrades, HttpStatus.OK);
    }

    @GetMapping("/user={id}")
    public ResponseEntity<List<Trade>> getAllTradesByUser(@PathVariable Long id) {
        List<Trade> allTradesByUser = service.getAllTradesByUser(id);
        return new ResponseEntity<>(allTradesByUser, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trade> getTradeById(@PathVariable Long id) {
        Trade result = service.getTradeById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @AllowedRoles({ Role.ADMIN })
    @GetMapping("/all/{status}")
    public ResponseEntity<List<Trade>> getTradesByStatus(@PathVariable String status) {
        List<Trade> tradesByStatus = service.getTradesByStatus(status);
        return new ResponseEntity<>(tradesByStatus, HttpStatus.OK);
    }

    @AllowedRoles({ Role.ADMIN })
    @GetMapping("/{tradeType}/{userId}") // filter by offering a trade or receiving a trade
    public ResponseEntity<List<Trade>> getTradeByUserAndType(@PathVariable Long userId, @PathVariable String tradeType) {
        List<Trade> tradesByUser = service.getTradesByUserAndTradeType(userId, tradeType);
        return new ResponseEntity<>(tradesByUser, HttpStatus.OK);
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
    public void approveTrade(@PathVariable Long id) throws MessagingException {
        service.approveTrade(id);
    }

    @PatchMapping("/decline/{id}")
    public void declineTrade(@PathVariable Long id) throws MessagingException {
        service.declineTrade(id);
    }

    @AllowedRoles({ Role.ADMIN })
    @DeleteMapping("/{id}")
    public HttpStatus cancelTrade(@PathVariable Long id) {
        service.cancelTrade(id);
        return HttpStatus.OK;
    }

}
