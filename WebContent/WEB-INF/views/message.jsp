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
                <h1>Wiadomość</h1>
            </div>
            <div class="menuPanel centered">
                <h2>${message}</h2>         
            </div>
        </div>
        
    <jsp:include page="footer.jsp"/>
    </body> 
</html>