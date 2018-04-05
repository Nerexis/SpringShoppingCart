<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <body>
        <jsp:include page="header.jsp"/>
        <jsp:include page="leftMenu.jsp"/>
    
        <div class="contentContainer">
            <div class="menuPanel">
                <h1>Formularz zamówienia</h1>
            </div>
            <div class="menuPanel centered">
                <form action='<c:url value="/makeOrderAction"/>' method="post" id="makeOrderForm">
                    <table class="dataTable">
                      <tr>
                        <td colspan="2">Dane do wysyłki</td>
                      </tr>
                      <tr>
                        <td>Imię:</td>
                        <td><input type="text" name="name" required></td>
                      </tr>
                      <tr>
                        <td>Nazwisko:</td>
                        <td><input type="text" name="surname" required></td>
                      </tr>      
                      <tr>
                        <td>Numer telefonu::</td>
                        <td><input type="text" name="phone" required></td>
                      </tr>                                          
                      <tr>
                        <td>Adres: </td>
                        <td><textarea name="address" form="makeOrderForm"></textarea></td>
                      </tr>
                      <tr>
                        <td colspan="2"><button type="submit">Złóż zamówienie</button></td>
                      </tr>
                    </table>
                    <input type="hidden" name="${_csrf.parameterName}"
                        value="${_csrf.token}" />                       
                </form>                 
            </div>
        </div>
        
    <jsp:include page="footer.jsp"/>
    </body> 
</html>