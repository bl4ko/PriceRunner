package si.fri.prpo.skupine7;

import javax.persistence.*;
import java.util.List;

@Entity(name = "store")
@NamedQueries(value =
        {
                @NamedQuery(name = "Store.getAll", query = "SELECT s FROM store s"),
                @NamedQuery(name = "Store.getById", query = "SELECT store FROM store WHERE store.id = ?1"),
                @NamedQuery(name = "Store.deleteById", query = "DELETE FROM store WHERE store.id = ?1"),
                @NamedQuery(name = "Store.deleteAll", query = "DELETE FROM store")
        })

public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    // Adds additional intermediate table
    @ManyToMany(mappedBy = "store", cascade = CascadeType.ALL)
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

}
