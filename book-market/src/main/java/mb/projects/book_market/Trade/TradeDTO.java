package mb.projects.book_market.Trade;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeDTO {

    @NotNull
    private Long userOffering_id;

    @NotNull
    private Long userReceiving_id;

    @NotNull
    private Long bookOffered_id;

    @NotNull
    private Long bookRequested_id;

    private String offerNote;

}
