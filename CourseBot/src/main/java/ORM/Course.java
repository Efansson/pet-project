package ORM;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Course")
public class Course {
    @Id
    @Column(name = "id")
    private int id;
    private String value;

    public Course () {
    }

    public Course ( int id, String value ) {
        this.id = id;
        this.value = value;
    }

    public int getId () {
        return id;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    public String getValue () {
        return value;
    }

    public void setValue ( String value ) {
        this.value = value;
    }

    @Override
    public String toString () {
        return value;
    }
}
