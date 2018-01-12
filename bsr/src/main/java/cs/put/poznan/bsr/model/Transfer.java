package cs.put.poznan.bsr.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    protected String source_account;

    protected int amount;

    protected String title;

    protected String source_name;

    protected String destination_name;
}
