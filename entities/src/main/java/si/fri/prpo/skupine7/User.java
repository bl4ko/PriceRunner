package si.fri.prpo.skupine7;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@NamedQueries(value =
        {
                @NamedQuery(name = "User.getAll", query = "SELECT u FROM user u"),
                @NamedQuery(name = "User.getById", query = "SELECT user FROM user WHERE user.id = ?1"),
                @NamedQuery(name = "User.deleteById", query = "DELETE FROM user WHERE user.id = ?1"),
                @NamedQuery(name = "User.deleteAll", query = "DELETE FROM user")
        })

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;


    // Adds additional intermediate table
    @OneToOne()
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public String getSurname() {return this.surname;}

    public void setName(String name) {
        this.name = name;
    }
    public void getSurname(String surname) {
        this.surname = surname;
    }

}
