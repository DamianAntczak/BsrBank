package cs.put.poznan.bsr.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "clients")
public class Client {

    @Id
    private String id;

    private String name;

    private String surname;

    private String password;
}
