package ir.takdev.Pic2pdf.model;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Img {
    @Id
    private long id;
    private String name;
    private Date date;
    public Img(long id, String name,Date date) {
        this.id = id;
        this.name = name;
        this.date=date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
