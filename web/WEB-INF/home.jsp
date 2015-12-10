<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <style>
            table {border: 1px solid black; border-collapse: collapse; margin: 0 auto}
            td {border: 1px solid black;}
        </style>
    </head>
    <body style="width: 800px; margin: 0 auto; text-align: center;">
        <h1>Home</h1>
        <section>
            <h3>Panier</h3> 
            <p>
                <c:choose>
                    <c:when test="${empty requestScope.cart}">
                        <c:out value="0 Item(s)" /></h5><br />
                        <c:out value="Price: 0 €" /></h5>
                    </c:when>
                    <c:otherwise>
                        <c:out value="${requestScope.cart.size()} Item(s)" /><br />
                        <c:out value="Price: ${requestScope.price} €" /><br />
                        <a href="<c:url value="/cart" />">Voir</a>
                    </c:otherwise>
                </c:choose>
            </p>
        </section>
        <section>
            <table>
                <thead>
                    <tr>
                        <th>ISBN</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Price</th>
                        <th>Add</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${requestScope.catalog}">
                        <tr>
                            <td><c:out value="${book.getIsbn()}" /></td>
                            <td><c:out value="${book.getTitle()}" /></td>
                            <td><c:out value="${book.getAuthor()}" /></td>
                            <td><c:out value="${book.getPrice()} €" /></td>
                            <td>
                                <form method="post">
                                    <input type="hidden" name="isbn" value="${book.getIsbn()}" />
                                    <input type="submit" value="Add" />
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>     

    </body>
</html>
