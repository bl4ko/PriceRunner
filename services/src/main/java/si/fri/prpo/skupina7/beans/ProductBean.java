package si.fri.prpo.skupina7.beans;

import si.fri.prpo.skupina7.Product;
import si.fri.prpo.skupina7.annotations.NoteCalls;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class ProductBean {

    Logger log = Logger.getLogger(ProductBean.class.getName());

    private UUID id;
    @PersistenceContext(unitName = "priceRunner-jpa")
    private EntityManager em;

    @PostConstruct
    public void afterCreate() {
        this.id = UUID.randomUUID();
        log.info("Creating a new instance of ProductBean[" + this.id + "]");
    }

    @PreDestroy
    public void destroy() {
        log.info("Destroying an instance of ProductBean[" + this.id + "]");
    }

    @NoteCalls
    public List<Product> getProducts() {
        List<Product> products = em.createNamedQuery("Product.getAll").getResultList();
        return products;
    }

    public int getProductCount() {
        return em.createNamedQuery("Product.getAll").getResultList().size();
    }


    public List<Product> getProductsCriteriaAPI() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        // Select all products
        cq.from(Product.class);
        return em.createQuery(cq).getResultList();
    }


    @Transactional
    public Product createProduct(Product product) {
        if (product != null) {
            em.persist(product);
        }
        return product;
    }


    public Product getProduct(Integer id) {
        return em.find(Product.class, id);
    }


    @Transactional
    public Product updateProduct(Integer productId, Product product) {
        Product p = em.find(Product.class, productId);
        product.setId(p.getId());
        em.merge(product);
        return product;
    }


    @Transactional
    public boolean deleteProduct(Integer id) {
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
            return true;
        }
        return false;
    }
}
