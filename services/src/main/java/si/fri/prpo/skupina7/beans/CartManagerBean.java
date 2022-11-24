package si.fri.prpo.skupina7.beans;

import si.fri.prpo.skupina7.Cart;
import si.fri.prpo.skupina7.Customer;
import si.fri.prpo.skupina7.Product;
import si.fri.prpo.skupina7.dtos.CartDto;
import si.fri.prpo.skupina7.dtos.ProductDto;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;

public class CartManagerBean {

    private Logger log = Logger.getLogger(CartManagerBean.class.getName());

    @Inject
    private CustomerBean customerBean;

    @Inject
    private ProductBean productBean;

    @Inject
    private CartBean cartBean;

    @PostConstruct
    public void init() {
        log.info("Initializing " + this.getClass().getSimpleName());
    }

    @PreDestroy
    public void destroy() {
        log.info("Destroying " + this.getClass().getSimpleName());
    }

    @Transactional
    public Cart createCart(CartDto cartDto) {
        Customer customer = customerBean.getCustomer(cartDto.getCustomerId());

        if (customer == null) {
            log.info("Customer with id " + cartDto.getCustomerId() + " does not exist");
            return null;
        }

        Cart cart = new Cart();
        cart.setCustomer(customer);
        customer.setCart(cart);
        cart.setName(cartDto.getName());
        cart.setDescription(cartDto.getDescription());
        cart.setCreatedTime(Instant.now().toString());

        return cartBean.createCart(cart);
    }

    @Transactional
    public Product addProductToCart(Integer cartId, ProductDto productDto) {

        Cart cart = cartBean.getCart(cartId);

        if (cart == null) {
            log.info("Cart with id " + cartId + " does not exist");
            return null;
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        List<Product> products = cart.getProducts();
        products.add(product);
        cart.setProducts(products);

        return cartBean.updateCart(cartId, cart).getProducts().get(cart.getProducts().size() - 1);
    }

    @Transactional
    public Product removeProductFromCart(Integer cartId, ProductDto productDto) {
        /* ΤODO */
        Cart cart = cartBean.getCart(cartId);

        if (cart == null) {
            log.info("Cart with id " + cartId + " does not exist");
            return null;
        }

        return cart;
    }
}
