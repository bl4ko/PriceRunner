package si.fri.prpo.skupina7;

import javax.persistence.*;

@Entity(name = "customer")
@NamedQueries(value =
        {
                @NamedQuery(name = "Customer.getAll", query = "SELECT u FROM customer u"),
                @NamedQuery(name = "Customer.getById", query = "SELECT u FROM customer u WHERE u.id = ?1"),
                @NamedQuery(name = "Customer.deleteById", query = "DELETE FROM customer u WHERE u.id = ?1"),
                @NamedQuery(name = "Customer.deleteAll", query = "DELETE FROM customer")
        })

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String username;
    private String email;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cart getCart() {
        return this.cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
