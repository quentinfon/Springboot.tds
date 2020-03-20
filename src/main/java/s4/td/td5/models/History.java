package s4.td.td5.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class History {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String comment;
    private String content;
    private Date date;

    @ManyToOne
    private Script version;

}
