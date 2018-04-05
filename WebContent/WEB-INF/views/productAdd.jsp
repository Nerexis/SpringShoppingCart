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
				<h1>Dodaj nowy produkt</h1>
			</div>
			<div class="menuPanel centered">
				<form action='<c:url value="/productAddAction"/>' method="post" id="productAddForm">
					<table class="dataTable">
					  <tr>
					    <td>Nazwa produktu:</td>
					    <td><input type="text" name="name" required></td>
					  </tr>
					  <tr>
					    <td>Opis: </td>
					    <td><textarea name="description" form="productAddForm"></textarea></td>
					  </tr>
					  <tr>
					    <td>Cena: </td>
					    <td><input type="number" step="0.01" pattern="\d+(\.\d{2})?" required></td>
					  </tr>			  
					  <tr>
					    <td colspan="2"><button type="submit">Dodaj produkt</button></td>
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