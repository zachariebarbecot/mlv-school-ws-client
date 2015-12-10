package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webservice.MLVBibService;
import webservice.MLVBibService_Service;

public class CartServlet extends HttpServlet {

    private static final String VIEW_HOME = "/WEB-INF/home.jsp";
    private static final String VIEW_CART = "/WEB-INF/cart.jsp";

    private static final String ATTR_CATALOG = "catalog";
    private static final String ATTR_CART = "cart";
    private static final String ATTR_PRICE = "price";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MLVBibService mbs = new MLVBibService_Service().getMLVBibServicePort();

        if (!mbs.getCart().isEmpty()) {
            request.setAttribute(ATTR_CART, mbs.getCart());
            request.setAttribute(ATTR_PRICE, mbs.getPriceCart());
            RequestDispatcher rd = request.getRequestDispatcher(VIEW_CART);
            rd.forward(request, response);
        } else {
            request.setAttribute(ATTR_CATALOG, mbs.catalog());
            RequestDispatcher rd = request.getRequestDispatcher(VIEW_HOME);
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!request.getParameter("isbn").isEmpty()) {
            long isbn = Long.parseLong(request.getParameter("isbn"));

            MLVBibService mbs = new MLVBibService_Service().getMLVBibServicePort();
            mbs.removeToCart(isbn);
            request.setAttribute(ATTR_CART, mbs.getCart());
            request.setAttribute(ATTR_PRICE, mbs.getPriceCart());
        }
        RequestDispatcher rd = request.getRequestDispatcher(VIEW_CART);
        rd.forward(request, response);
    }
}
