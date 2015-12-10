package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webservice.MLVBibService;
import webservice.MLVBibService_Service;

public class BuyCartServlet extends HttpServlet {

    private static final String VIEW_HOME = "/WEB-INF/home.jsp";
    private static final String VIEW_CART = "/WEB-INF/cart.jsp";

    private static final String ATTR_CATALOG = "catalog";
    private static final String ATTR_CART = "cart";
    private static final String ATTR_PRICE = "price";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MLVBibService mbs = new MLVBibService_Service().getMLVBibServicePort();
        if (!request.getParameter("cart").isEmpty()) {
            mbs.buyBooksInCart();
            request.setAttribute(ATTR_CATALOG, mbs.catalog());
            request.setAttribute(ATTR_CART, mbs.getCart());
            request.setAttribute(ATTR_PRICE, mbs.getPriceCart());
            RequestDispatcher rd = request.getRequestDispatcher(VIEW_HOME);
            rd.forward(request, response);
        } else {
            request.setAttribute(ATTR_CART, mbs.getCart());
            request.setAttribute(ATTR_PRICE, mbs.getPriceCart());
            RequestDispatcher rd = request.getRequestDispatcher(VIEW_CART);
            rd.forward(request, response);
        }

    }

}
