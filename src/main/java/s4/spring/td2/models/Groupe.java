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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAliases() {
        return aliases;
    }

    public Organization getOrganization() {
        return organization;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
