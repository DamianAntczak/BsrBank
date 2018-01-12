package cs.put.poznan.bsr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class History extends Transfer {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date timestamp;

    private String nrb;

    private BigDecimal balanceBefore;

    private BigDecimal balanceAfter;

    public void setTransfer(Transfer transfer) {
        this.source_account = transfer.source_account;
        this.amount = transfer.amount;
        this.title = transfer.title;
        this.source_name = transfer.source_name;
        this.destination_name = transfer.destination_name;
    }
}
