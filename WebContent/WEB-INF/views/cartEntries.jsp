<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="contentContainer fullWidth">
            <div class="menuPanel">
                <h1>Twój koszyk</h1>
            </div>
            <div class="menuPanel">
                <table class="dataTable">
                    <thead>
                        <tr>
                            <td>Nazwa</td>
                            <td>Zamawiana ilość</td>
                            <td>Cena (PLN)</td>
                            <td class="centered">Razem (PLN)</td>
                            <td class="centered">Akcja</td>
                            
                        </tr>
                    </thead>
                    <c:forEach items="${cartEntries}" var="cartEntry">
                        <tr>
                            <td>${cartEntry.product.name }</td>
                            <td>${cartEntry.quantity}</td>
                            <td>${cartEntry.product.price} PLN</td>
                            <td class="centered">${cartEntry.product.price*cartEntry.quantity}</td>
                            <td class="centered">
                                  <form action='<c:url value="/cartChangeQuantity"/>' method="post">
                                      <input type="number" name="newQuantity" 
                                           step="1" value='${cartEntry.quantity}' min="1" size="5" class="centered"></input>
                                      <input type="submit" class="link" value="Zmień ilość"></input>
                                      <input type="hidden" name="idCartEntry" value="${cartEntry.id}"></input>
                                      <input type="hidden" name="${_csrf.parameterName}"
                                          value="${_csrf.token}" />                                       
                                  </form>                            
                                  <form action='<c:url value="/cartEntryRemove"/>' method="post">
                                      <input type="submit" class="link" value="Usuń z koszyka"></input>
                                      <input type="hidden" name="idCartEntry" value="${cartEntry.id}"></input>
                                      <input type="hidden" name="${_csrf.parameterName}"
                                          value="${_csrf.token}" />                                       
                                  </form>
                                  <c:set var = "total" value ="${total+(cartEntry.product.price*cartEntry.quantity)}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td class="centered">Suma: <c:out value = "${total}"/></td>
                        <td class="centered">
                            <a href='<c:url value="/makeOrder"/>'>
                                Złóż zamówienie
                            </a>
                        </td>
                        
                    </tr>
                </table>            
            </div>
        </div>
        
    <jsp:include page="footer.jsp"/>
    </body>
</html>