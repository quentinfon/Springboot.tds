package s4.td.td5.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class Script {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;


    private String title;
    private String description;

    @Column(length = 5000)
    private String content;

    private Date creationDate;

    private String strCreationDate;

    @JsonManagedReference
    @ManyToOne
    private Category category;

    @JsonManagedReference
    @ManyToOne
    private User owner;

    @JsonManagedReference
    @ManyToOne
    private Language language;

    @JsonBackReference
    @OneToMany(cascade=CascadeType.ALL,mappedBy="script", fetch = FetchType.EAGER)
    private List<History> history;


    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        setStrCreationDate(dateFormat.format(creationDate));
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    public String getStrCreationDate() {
        return strCreationDate;
    }

    public void setStrCreationDate(String strCreationDate) {
        this.strCreationDate = strCreationDate;
    }
}