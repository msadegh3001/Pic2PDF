
package ir.takdev.Pic2pdf.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class PDF {
    @Id
    private long id;
    private String size;
    private String name;

    public PDF(String size, String name) {
        this.size = size;
        this.name = name;
    }

    public PDF(long id, String size, String name) {
        this.id = id;
        this.size = size;
        this.name = name;
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
}

