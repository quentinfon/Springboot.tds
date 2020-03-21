package s4.td.td5.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class History {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String comment;

    @Column(length = 5000)
    private String content;
    private Date date;

    @JsonManagedReference
    @ManyToOne
    private Script script;

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

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public int getId() {
        return id;
    }
}
