package si.fri.prpo.skupina7;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@ApplicationScoped
public class ProductBean {

    @PersistenceContext(unitName = "priceRunner-jpa")
    private EntityManager em;

    public List<Product> getProducts() {
        Query q = em.createNamedQuery("Product.getAll");
        // Convert resultSet to List<Product>
        List<Product> products = (List<Product>) q.getResultList();
        return products;
    }

    // Create a method that returns all entities using Criteria API
    public List<Product> getProductsCriteria() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        // Select all products
        cq.from(Product.class);
        // Convert resultSet to List<Product>
        return em.createQuery(cq).getResultList();
    }
}
