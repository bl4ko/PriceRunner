package si.fri.prpo.skupina7;

import javax.persistence.*;

@Entity(name = "product_store_price")
@NamedQueries(value =
        {
                @NamedQuery(name = "ProductStorePrice.getStoresByProductId",
                        query = "SELECT psp.id, s.id, p.id, psp.price FROM product_store_price psp " +
                                "join product p on p.id = psp.product.id " +
                                "join store s on s.id = psp.store.id  WHERE psp.product.id" +
                                " = ?1"),
                @NamedQuery(name = "ProductStorePrice.getAll", query = "SELECT psp FROM product_store_price psp"),

        })
public class ProductStorePrice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id")
    private Store store;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer price;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
