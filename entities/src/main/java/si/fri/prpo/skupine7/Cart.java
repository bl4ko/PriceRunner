package si.fri.prpo.skupine7;

import javax.persistence.*;
import java.util.List;

@Entity(name = "cart")
@NamedQueries(value =
        {
                @NamedQuery(name = "Cart.getAll", query = "SELECT c FROM cart c"),
                @NamedQuery(name = "Cart.getById", query = "SELECT cart FROM user WHERE cart.id = ?1"),
                @NamedQuery(name = "Cart.deleteById", query = "DELETE FROM cart WHERE cart.id = ?1"),
                @NamedQuery(name = "Cart.deleteAll", query = "DELETE FROM cart")
        })

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name = "name")
//    private String name;

    // Adds additional intermediate table
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private User user;

    @ManyToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Product> products;

//    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
//    private Store store;

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
