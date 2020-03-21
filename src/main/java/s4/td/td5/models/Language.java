package s4.td.td5.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Language {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(unique=true)
    private String name;

    @JsonBackReference
    @OneToMany(cascade=CascadeType.PERSIST,mappedBy="language")
    private List<Script> scripts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Script> getScripts() {
        return scripts;
    }

    public void setScripts(List<Script> scripts) {
        this.scripts = scripts;
    }

    public int getId() {
        return id;
    }
}
