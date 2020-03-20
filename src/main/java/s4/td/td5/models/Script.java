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
    private User language;

    @OneToMany(cascade=CascadeType.PERSIST,mappedBy="version")
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
}