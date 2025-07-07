package mb.projects.book_market.Trade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTradeDTO {

    private Long bookOffered_id;

    private Long bookRequested_id;

    private String offerNote;    

}