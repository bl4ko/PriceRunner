package si.fri.prpo.skupina7;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Entity(name = "store")
@NamedQueries(value =
        {
                @NamedQuery(name = "Store.getAll", query = "SELECT s FROM store s"),
                @NamedQuery(name = "Store.getById", query = "SELECT s FROM store s WHERE s.id = ?1"),
                @NamedQuery(name = "Store.deleteById", query = "DELETE FROM store s WHERE s.id = ?1"),
                @NamedQuery(name = "Store.deleteAll", query = "DELETE FROM store")
        })

public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    // Adds additional intermediate table
    @JsonbTransient
    @ManyToMany(mappedBy = "stores", cascade = CascadeType.ALL)
    private List<Product> products;

    @JsonbTransient
    @OneToMany(mappedBy = "store", fetch = FetchType.EAGER)
    private List<ProductStorePrice> productStorePrice;


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

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<ProductStorePrice> getProductStorePrice() {
        return this.productStorePrice;
    }

    public void setProductStorePrice(List<ProductStorePrice> productStorePrice) {
        this.productStorePrice = productStorePrice;
    }

}
