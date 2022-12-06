package si.fri.prpo.skupina7.beans;

import si.fri.prpo.skupina7.Cart;

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
public class CartBean {

    Logger log = Logger.getLogger(CartBean.class.getName());

    private UUID id;
    @PersistenceContext(unitName = "priceRunner-jpa")
    private EntityManager em;

    @PostConstruct
    public void afterCreate() {
        this.id = UUID.randomUUID();
        log.info("Creating a new instance of CartBean[" + this.id + "]");
    }

    @PreDestroy
    public void destroy() {
        log.info("Destroying an instance of CartBean[" + this.id + "]");
    }

    public List<Cart> getCarts() {
        List<Cart> Carts = em.createNamedQuery("Cart.getAll").getResultList();
        return Carts;
    }

    public int getCartCount() {
        return em.createNamedQuery("Cart.getAll").getResultList().size();
    }

    @Transactional
    public Cart createCart(Cart cart) {
        if (cart != null) {
            em.persist(cart);
        }
        return cart;
    }


    public Cart getCart(Integer id) {
        return em.find(Cart.class, id);
    }

    @Transactional
    public Cart updateCart(Integer CartId, Cart Cart) {
        Cart p = em.find(Cart.class, CartId);
        Cart.setId(p.getId());
        em.merge(Cart);
        return Cart;
    }

    @Transactional
    public boolean deleteCart(Integer id) {
        Cart cart = em.find(Cart.class, id);
        if (cart != null) {
            em.remove(cart);
            return true;
        }
        return false;
    }
}
