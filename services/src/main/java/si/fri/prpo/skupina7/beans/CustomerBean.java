package si.fri.prpo.skupina7.beans;

import si.fri.prpo.skupina7.Customer;

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
public class CustomerBean {

    Logger log = Logger.getLogger(CustomerBean.class.getName());

    private UUID id;
    @PersistenceContext(unitName = "priceRunner-jpa")
    private EntityManager em;

    @PostConstruct
    public void afterCreate() {
        this.id = UUID.randomUUID();
        log.info("Creating a new instance of CustomerBean[" + this.id + "]");
    }

    @PreDestroy
    public void destroy() {
        log.info("Destroying an instance of CustomerBean[" + this.id + "]");
    }

    public List<Customer> getCustomers() {
        List<Customer> Customers = em.createNamedQuery("Customer.getAll").getResultList();
        return Customers;
    }


    @Transactional
    public Customer createCustomer(Customer Customer) {
        if (Customer != null) {
            em.persist(Customer);
        }
        return Customer;
    }


    public Customer getCustomer(Integer id) {
        return em.find(Customer.class, id);
    }

    @Transactional
    public Customer updateCustomer(Integer CustomerId, Customer Customer) {
        Customer p = em.find(Customer.class, CustomerId);
        Customer.setId(p.getId());
        em.merge(Customer);
        return Customer;
    }

    @Transactional
    public boolean deleteCustomer(Integer id) {
        Customer Customer = em.find(Customer.class, id);
        if (Customer != null) {
            em.remove(Customer);
            return true;
        }
        return false;
    }
}