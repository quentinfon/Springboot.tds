package s4.spring.td2.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String suspended;

    @ManyToOne
    private Organization organization;

    @ManyToMany(mappedBy="users")
    private List<Groupe> groupes;

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSuspended() {
        return suspended;
    }

    public Organization getOrganization() {
        return organization;
    }

    public List<Groupe> getGroupes() {
        return groupes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSuspended(String suspended) {
        this.suspended = suspended;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public void setGroupes(List<Groupe> groupes) {
        this.groupes = groupes;
    }
}
