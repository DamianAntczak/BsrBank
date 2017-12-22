package cs.put.poznan.bsr.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Getter
    @Setter
    private String nrb;

    @Getter
    @Setter
    private BigDecimal amount;
}
