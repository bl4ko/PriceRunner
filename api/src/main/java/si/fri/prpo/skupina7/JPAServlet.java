package si.fri.prpo.skupina7;


import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private ProductBean productBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Hello from JPA Servlet, using named query for all products:");
        List<Product> products = productBean.getProducts();

         // Display products
         for (Product product : products) {
            resp.getWriter().println(product.getName());
         }

         resp.getWriter().println("Using Criteria API for all products:");
         List<Product> productsCriteria = productBean.getProductsCriteria();

         for (Product product : productsCriteria) {
            resp.getWriter().println(product.getName());
         }
    }
}