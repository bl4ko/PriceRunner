package si.fri.prpo.skupina7.beans;

import si.fri.prpo.skupina7.Category;
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
public class CategoryBean {

    Logger log = Logger.getLogger(CategoryBean.class.getName());

    private UUID id;
    @PersistenceContext(unitName = "priceRunner-jpa")
    private EntityManager em;

    @PostConstruct
    public void afterCreate() {
        this.id = UUID.randomUUID();
        log.info("Creating a new instance of CategoryBean[" + this.id + "]");
    }

    @PreDestroy
    public void destroy() {
        log.info("Destroying an instance of CategoryBean[" + this.id + "]");
    }

    @NoteCalls
    public List<Category> getCategories() {
        List<Category> categories = em.createNamedQuery("Category.getAll").getResultList();
        return categories;
    }

    public int getCategoryCount() {
        return em.createNamedQuery("Category.getAll").getResultList().size();
    }

    @Transactional
    @NoteCalls
    public Category createCategory(Category category) {
        if (category != null) {
            em.persist(category);
        }
        return category;
    }


    @NoteCalls
    public Category getCategory(Integer id) {
        return em.find(Category.class, id);
    }

    @Transactional
    @NoteCalls
    public Category updateCategory(Integer categoryId, Category category) {
        Category p = em.find(Category.class, categoryId);
        category.setId(p.getId());
        em.merge(category);
        return category;
    }

    @Transactional
    @NoteCalls
    public boolean deleteCategory(Integer id) {
        Category category = em.find(Category.class, id);
        if (category != null) {
            em.remove(category);
            return true;
        }
        return false;
    }
}
