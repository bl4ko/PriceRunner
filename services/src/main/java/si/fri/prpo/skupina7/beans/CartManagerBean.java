package si.fri.prpo.skupina7.beans;

import si.fri.prpo.skupina7.Customer;
import si.fri.prpo.skupina7.dtos.ShoppingCartDto;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.logging.Logger;

public class ShoppingCartManagerBean {

    private Logger log = Logger.getLogger(ShoppingCartManagerBean.class.getName());

    @Inject
    private CustomerBean customerBean;

    @Inject
    private ProductBean productBean;

    @Inject
    private CartBean cartBean;

    @PostConstruct
    public void init() {
        log.info("Initializing ShoppingCartManagerBean");
    }

    @PreDestroy
    public void destroy() {
        log.info("Destroying ShoppingCartManagerBean");
    }

    @Transactional
    public  createShoppingCart(ShoppingCartDto shoppingCartDto) {
        Customer customer = customerBean.getCustomer(shoppingCartDto.getCustomerId());

        if (customer == null) {
            log.info("Customer with id " + shoppingCartDto.getCustomerId() + " does not exist");
            return null;
        }
    }

}
