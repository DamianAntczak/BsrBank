package cs.put.poznan.bsr.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "account")
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

    @Getter
    @Setter
    private String clientId;
}
