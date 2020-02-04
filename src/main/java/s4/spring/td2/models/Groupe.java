package s4.spring.td2.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Groupe {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String name;
    private String email;
    private String aliases;

    @ManyToOne
    private Organization organization;

    @ManyToMany
    @JoinTable(name = "user_group")
    private List<User> users;

}
