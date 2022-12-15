package si.fri.prpo.skupina7.servlets;


import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.prpo.skupina7.Product;
import si.fri.prpo.skupina7.beans.ProductBean;

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
        QueryParameters query = new QueryParameters();
        query.setLimit(10);
        query.setOffset(0);
        List<Product> products = productBean.getProducts(query);

        // Display products
        for (Product product : products) {
            resp.getWriter().println(product.getName());
        }

        resp.getWriter().println("\nUsing Criteria API for all products:");
        List<Product> productsCriteria = productBean.getProductsCriteriaAPI();

        for (Product product : productsCriteria) {
            resp.getWriter().println(product.getName());
        }
    }
}
