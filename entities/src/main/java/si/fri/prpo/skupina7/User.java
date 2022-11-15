package si.fri.prpo.skupine7;

import javax.persistence.*;

@Entity(name = "user")
@NamedQueries(value =
        {
                @NamedQuery(name = "User.getAll", query = "SELECT u FROM user u"),
                @NamedQuery(name = "User.getById", query = "SELECT u FROM user u WHERE u.id = ?1"),
                @NamedQuery(name = "User.deleteById", query = "DELETE FROM user u WHERE u.id = ?1"),
                @NamedQuery(name = "User.deleteAll", query = "DELETE FROM user")
        })

public class User {
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

    public void setSurname(String surname) {
        this.surname = surname;
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
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
