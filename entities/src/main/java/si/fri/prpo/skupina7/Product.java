package si.fri.prpo.skupina7;

import javax.persistence.*;
import java.util.List;

@Entity(name = "product")
@NamedQueries(value =
        {
                @NamedQuery(name = "Product.getAll", query = "SELECT p FROM product p"),
                @NamedQuery(name = "Product.getById", query = "SELECT p FROM product p WHERE p.id = ?1"),
                @NamedQuery(name = "Product.deleteById", query = "DELETE FROM product p WHERE p.id = ?1"),
                @NamedQuery(name = "Product.deleteAll", query = "DELETE FROM product"),
                @NamedQuery(name = "Product.getByCategoryId", query = "SELECT p FROM product p WHERE p.category.id = ?1"),
                @NamedQuery(name = "Product.getByStoreId", query = "SELECT p FROM product p WHERE ?1 MEMBER OF p.stores")
        })
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private Integer price;

    // Add column for category
    // Each category has one or more products
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinColumn(name = "cart_id")
    private List<Cart> carts;

    @ManyToMany
    @JoinColumn(name = "store_id")
    private List<Store> stores;


    // getter and setter methods
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
