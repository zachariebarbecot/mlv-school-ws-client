package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webservice.MLVBibService;
import webservice.MLVBibService_Service;

public class HomeServlet extends HttpServlet {

    private static final String VIEW_HOME = "/WEB-INF/home.jsp";
    private static final String ATTR_CATALOG = "catalog";
    private static final String ATTR_CART = "cart";
    private static final String ATTR_PRICE = "price";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MLVBibService mbs = new MLVBibService_Service().getMLVBibServicePort();
        request.setAttribute(ATTR_CATALOG, mbs.catalog());
        request.setAttribute(ATTR_CART, mbs.getCart());
        request.setAttribute(ATTR_PRICE, mbs.getPriceCart());
        RequestDispatcher rd = request.getRequestDispatcher(VIEW_HOME);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!request.getParameter("isbn").isEmpty()) {
            long isbn = Long.parseLong(request.getParameter("isbn"));

            MLVBibService mbs = new MLVBibService_Service().getMLVBibServicePort();
            mbs.addToCart(isbn);
            request.setAttribute(ATTR_CATALOG, mbs.catalog());
            request.setAttribute(ATTR_CART, mbs.getCart());
            request.setAttribute(ATTR_PRICE, mbs.getPriceCart());
        }
        RequestDispatcher rd = request.getRequestDispatcher(VIEW_HOME);
        rd.forward(request, response);
    }

}
