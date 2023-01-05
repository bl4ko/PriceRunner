package si.fri.prpo.skupina7.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
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

    @NoteCalls
    public List<Product> getProducts(QueryParameters queryParameters) {
        return JPAUtils.queryEntities(em, Product.class, queryParameters);
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
    @NoteCalls
    public Product createProduct(Product product) {
        if (product != null) {
            em.persist(product);
        }
        return product;
    }


    @NoteCalls
    public Product getProduct(Integer id) {
        return em.find(Product.class, id);
    }


    @Transactional
    @NoteCalls
    public Product updateProduct(Integer productId, Product product) {
        Product p = em.find(Product.class, productId);
        product.setId(p.getId());
        em.merge(product);
        return product;
    }


    @Transactional
    @NoteCalls
    public boolean deleteProduct(Integer id) {
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
            return true;
        }
        return false;
    }

    @NoteCalls
    public List<Product> getProductsByCategoryId(Integer categoryId) {
        return (List<Product>) em.createNamedQuery("Product.getByCategoryId").setParameter(1, categoryId).getResultList();
    }

    // Get all products that are in a store with a given id
    @NoteCalls
    public List<Product> getProductsByStoreId(Integer storeId) {
        return (List<Product>) em.createNamedQuery("Product.getByStoreId").setParameter(1, storeId).getResultList();
    }
}
