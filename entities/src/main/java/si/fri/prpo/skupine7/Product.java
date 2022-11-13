package si.fri.prpo.skupine7;

import javax.persistence.*;

@Entity(name = "product")
@NamedQueries(value =
        {
                @NamedQuery(name = "Product.getAll", query = "SELECT p FROM product p"),
                @NamedQuery(name = "Product.getById", query = "SELECT p FROM product p WHERE p.id = ?1"),
                @NamedQuery(name = "Product.deleteById", query = "DELETE FROM product WHERE product.id = ?1"),
                @NamedQuery(name = "Product.deleteAll", query = "DELETE FROM product")
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
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


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
}
