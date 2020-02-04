package s4.spring.td2.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Organization {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String name;
    private String domain;
    private String aliases;

    @OneToMany(cascade= CascadeType.ALL,mappedBy="organization")
    private List<Groupe> groupes;

    @OneToMany(cascade=CascadeType.ALL,mappedBy="organization")
    private List<User> users;
}