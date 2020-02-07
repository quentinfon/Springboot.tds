package s4.spring.td2.models;

import javax.persistence.*;
import java.util.*;

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


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }

    public String getAliases() {
        return aliases;
    }

    public List<Groupe> getGroupes() {
        return groupes;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public void setGroupes(List<Groupe> groupes) {
        this.groupes = groupes;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void ajoutGroupe(Groupe g){
        if(groupes == null){
            groupes = new ArrayList<Groupe>();
        }
        if (!groupes.contains(g)){
            groupes.add(g);
        }
    }
}