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
				<h1>Logowanie do sklepu</h1>
			</div>
			<div class="menuPanel centered">
				<form action='<c:url value="/login"/>' method="post">
					<table class="dataTable">
					  <tr>
					    <td>Nazwa użytkownika:</td>
					    <td><input type="text" name="username" required></td>
					  </tr>
					  <tr>
					    <td>Hasło: </td>
					    <td><input type="password" name="password" required></td>
					  </tr>
					  <tr>
					    <td colspan="2"><button type="submit">Login</button></td>					    
					  </tr>
					  <tr>
					  	<td colspan="2">					
							<c:if test="${not empty error}">
								<div class="error">Błąd: ${error}</div>
							</c:if>
							<c:if test="${not empty msg}">
								<div class="msg">${msg}</div>
							</c:if>
					  	</td>
					  </tr>
					</table>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />					
				</form>		
				<br/><a href='<c:url value="/register"/>'>Rejestracja nowego konta</a>			
			</div>
		</div>
		
	<jsp:include page="footer.jsp"/>
	</body>
</html>