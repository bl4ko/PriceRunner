package si.fri.prpo.skupina7.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.skupina7.Store;
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
public class StoreBean {

    Logger log = Logger.getLogger(StoreBean.class.getName());

    private UUID id;
    @PersistenceContext(unitName = "priceRunner-jpa")
    private EntityManager em;

    @PostConstruct
    public void afterCreate() {
        this.id = UUID.randomUUID();
        log.info("Creating a new instance of StoreBean[" + this.id + "]");
    }

    @PreDestroy
    public void destroy() {
        log.info("Destroying an instance of StoreBean[" + this.id + "]");
    }

    @NoteCalls
    public List<Store> getStores() {
        List<Store> stores = em.createNamedQuery("Store.getAll").getResultList();
        return stores;
    }

    @NoteCalls
    public List<Store> getStores(QueryParameters query) {
        return JPAUtils.queryEntities(em, Store.class, query);
    }

    public int getStoreCount() {
        return em.createNamedQuery("Store.getAll").getResultList().size();
    }


    @Transactional
    @NoteCalls
    public Store createStore(Store store) {
        if (store != null) {
            em.persist(store);
        }
        return store;
    }


    @NoteCalls
    public Store getStore(Integer id) {
        return em.find(Store.class, id);
    }

    @Transactional
    @NoteCalls
    public Store updateStore(Integer storeId, Store store) {
        Store p = em.find(Store.class, storeId);
        store.setId(p.getId());
        em.merge(store);
        return store;
    }

    @Transactional
    @NoteCalls
    public boolean deleteStore(Integer id) {
        Store store = em.find(Store.class, id);
        if (store != null) {
            em.remove(store);
            return true;
        }
        return false;
    }
}
