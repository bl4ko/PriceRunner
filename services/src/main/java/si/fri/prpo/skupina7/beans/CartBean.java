package si.fri.prpo.skupina7.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.prpo.skupina7.Cart;
import si.fri.prpo.skupina7.Product;
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

    @NoteCalls
    public List<Cart> getCarts() {
        List<Cart> Carts = em.createNamedQuery("Cart.getAll").getResultList();
        return Carts;
    }

    @NoteCalls
    public List<Cart> getCarts(QueryParameters query) {
        return JPAUtils.queryEntities(em, Cart.class, query);
    }

    public int getCartCount() {
        return em.createNamedQuery("Cart.getAll").getResultList().size();
    }

    @Transactional
    @NoteCalls
    public Cart createCart(Cart cart) {
        if (cart != null) {
            em.persist(cart);
        }
        return cart;
    }


    @NoteCalls
    public Cart getCart(Integer id) {
        return em.find(Cart.class, id);
    }

    @Transactional
    @NoteCalls
    public Cart updateCart(Integer CartId, Cart Cart) {
        Cart p = em.find(Cart.class, CartId);
        Cart.setId(p.getId());
        em.merge(Cart);
        return Cart;
    }

    @Transactional
    @NoteCalls
    public boolean deleteCart(Integer id) {
        Cart cart = em.find(Cart.class, id);
        if (cart != null) {
            em.remove(cart);
            return true;
        }
        return false;
    }

    @Transactional
    @NoteCalls
    public boolean addProductToCart(Integer cartId, Integer productId) {
        Product product = em.find(Product.class, productId);
        Cart cart = em.find(Cart.class, cartId);
        // Check if cart and product exist
        if (cart != null && product != null) {
            // Check if product is already in cart
            if (cart.getProducts().contains(product)) {
                return false;
            }
            // Add product to cart
            cart.getProducts().add(product);
            em.merge(cart);
            return true;
        }
        return false;
    }

    // Remove product from cart
    @Transactional
    @NoteCalls
    public boolean removeProductFromCart(Integer cartId, Integer productId) {
        Product product = em.find(Product.class, productId);
        Cart cart = em.find(Cart.class, cartId);
        // Check if cart and product exist
        if (cart != null && product != null) {
            // Check if product is in cart
            if (!cart.getProducts().contains(product)) {
                return false;
            }
            // Remove product from cart
            cart.getProducts().remove(product);
            em.merge(cart);
            return true;
        }
        return false;
    }
}
