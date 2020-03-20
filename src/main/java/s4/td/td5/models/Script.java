package s4.td.td5.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Script {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;


    private String title;
    private String description;
    private String content;
    private Date creationDate;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User owner;

    @ManyToOne
    private Language language;

    @OneToMany(cascade=CascadeType.PERSIST,mappedBy="script")
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
}