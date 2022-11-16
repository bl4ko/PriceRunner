package si.fri.prpo.skupina7;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class ProductBean {

    @PersistenceContext(unitName = "priceRunner-jpa")
    private EntityManager em;

//    public List<Product> getProducts() {
////        Query q = em.createNamedQuery("Product.getAll");
////        // Convert resultSet to List<Product>
////        List<Product> products = (List<Product>) q.getResultList();
////        return products;
//        return null;
//    }
}
