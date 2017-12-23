package cs.put.poznan.bsr.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "accounts")
public class Account {

    @Getter
    @Setter
    @Id
    private String nrb;

    @Getter
    @Setter
    private BigDecimal amount;
}
