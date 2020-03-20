package s4.td.td5.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class History {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String comment;
    private String content;
    private Date date;

    @ManyToOne
    private Script version;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Script getVersion() {
        return version;
    }

    public void setVersion(Script version) {
        this.version = version;
    }

    public int getId() {
        return id;
    }
}
