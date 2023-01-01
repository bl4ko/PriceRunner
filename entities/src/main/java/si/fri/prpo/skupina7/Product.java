package si.fri.prpo.skupina7;

import javax.json.bind.annotation.JsonbTransient;
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

    // Add column for category
    // Each category has one or more products
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonbTransient
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<ProductStorePrice> productStorePrices;

    @JsonbTransient
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

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Cart> getCarts() {
        return this.carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<Store> getStores() {
        return this.stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public List<ProductStorePrice> getProductStorePrices() {
        return this.productStorePrices;
    }

    public void setProductStorePrices(List<ProductStorePrice> productStorePrices) {
        this.productStorePrices = productStorePrices;
    }


}
