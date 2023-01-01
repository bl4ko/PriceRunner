package si.fri.prpo.skupina7;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Entity(name = "category")
@NamedQueries(value =
        {
                @NamedQuery(name = "Category.getAll", query = "SELECT c FROM category c"),
                @NamedQuery(name = "Category.getById", query = "SELECT c FROM category c WHERE c.id = ?1"),
                @NamedQuery(name = "Category.deleteById", query = "DELETE FROM category c WHERE c.id = ?1"),
                @NamedQuery(name = "Category.deleteAll", query = "DELETE FROM category")
        })

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    // Adds additional intermediate table
    @JsonbTransient
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products;

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

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
