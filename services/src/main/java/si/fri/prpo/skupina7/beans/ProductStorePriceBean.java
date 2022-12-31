package si.fri.prpo.skupina7.beans;

import si.fri.prpo.skupina7.ProductStorePrice;
import si.fri.prpo.skupina7.annotations.NoteCalls;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class ProductStorePriceBean {

    Logger log = Logger.getLogger(ProductStorePriceBean.class.getName());

    private UUID id;

    @PersistenceContext(unitName = "priceRunner-jpa")
    private EntityManager em;

    @PostConstruct
    public void afterCreate() {
        this.id = UUID.randomUUID();
        log.info("Creating a new instance of ProductStorePriceBean[" + this.id + "]");
    }

    @PreDestroy
    public void destroy() {
        log.info("Destroying an instance of ProductStorePriceBean[" + this.id + "]");
    }

    public List<ProductStorePrice> getProductStorePrices() {
        List<ProductStorePrice> productStorePrices = em.createNamedQuery("ProductStorePrice.getAll").getResultList();
        return productStorePrices;
    }

    public int getProductStorePriceCount(Integer productId) {
        return em.createNamedQuery("ProductStorePrice.getStoresByProductId").setParameter(1, productId).getResultList().size();
    }

    public List<ProductStorePrice> getStorePriceTuples(Integer storeId) {
        List<ProductStorePrice> productStorePrices = em.createNamedQuery("ProductStorePrice.getStoresByProductId").setParameter(1, storeId).getResultList();
        Logger.getLogger("ProductStorePriceBean").info(productStorePrices.toString());

        return productStorePrices;
    }

    @Transactional
    @NoteCalls
    public ProductStorePrice createProductStorePrice(ProductStorePrice productStorePrice) {
        if (productStorePrice != null) {
            em.persist(productStorePrice);
        }
        return productStorePrice;
    }

    @Transactional
    @NoteCalls
    public boolean deleteProductStorePrice(Integer id) {
        ProductStorePrice productStorePrice = em.find(ProductStorePrice.class, id);
        if (productStorePrice != null) {
            em.remove(productStorePrice);
            return true;
        }
        return false;
    }
}
