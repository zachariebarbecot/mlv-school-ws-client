<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panier</title>
        <style>
            table {border: 1px solid black; border-collapse: collapse; margin: 0 auto}
            td {border: 1px solid black;}
        </style>
    </head>
    <body style="width: 800px; margin: 0 auto; text-align: center;">
        <h1>Panier</h1>
        <c:choose>
            <c:when test="${empty requestScope.cart}">
                <p>
                    Panier vide.
                </p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>ISBN</th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Price</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="book" items="${requestScope.cart}">
                            <tr>
                                <td>${book.getIsbn()}</td>
                                <td>${book.getTitle()}</td>
                                <td>${book.getAuthor()}</td>
                                <td>${book.getPrice()}</td>
                                <td>
                                    <form method="post">
                                        <input type="hidden" name="isbn" value="${book.getIsbn()}" />
                                        <input type="submit" name="action" value="X" />
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <p>
                    <strong><c:out value="Price: ${requestScope.price} €" /></strong>
                <form method="post" action="buy">
                    <input type="hidden" name="cart" value="${requestScope.cart}" />
                    <input type="submit" value="Buy" />
                </form>
                <form method="post">
                    <input type="submit" name="action" value="Empty" />
                </form>
            </p>
        </c:otherwise>
    </c:choose>
</body>
</html>
