package cs.put.poznan.bsr.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "clients")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "client", propOrder = {
        "id",
        "name",
        "password",
        "surname"
})
public class Client {

    @Id
    private String id;

    private String name;

    private String surname;

    private String password;
}
