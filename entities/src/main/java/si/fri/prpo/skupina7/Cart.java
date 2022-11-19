package si.fri.prpo.skupina7;

import javax.persistence.*;
import java.util.List;

@Entity(name = "cart")
@NamedQueries(value =
        {
                @NamedQuery(name = "Cart.getAll", query = "SELECT c FROM cart c"),
                @NamedQuery(name = "Cart.getById", query = "SELECT c FROM cart c WHERE c.id = ?1"),
                @NamedQuery(name = "Cart.deleteById", query = "DELETE FROM cart c WHERE c.id = ?1"),
                @NamedQuery(name = "Cart.deleteAll", query = "DELETE FROM cart")
        })

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Adds additional intermediate table
    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    private Customer customer;

    @ManyToMany(mappedBy = "carts", cascade = CascadeType.ALL)
    private List<Product> products;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
