package s4.td.td5.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(unique=true)
    private String name;

    @OneToMany(cascade=CascadeType.PERSIST,mappedBy="category")
    private List<Script> scripts;

}
