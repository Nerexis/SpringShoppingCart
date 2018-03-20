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
				<h1>Witamy w sklepie internetowym!</h1>
			</div>
			<div class="menuPanel">
				<table class="dataTable">
					<thead>
						<tr>
							<td>Nazwa</td>
							<td>Opis</td>
							<td>Cena</td>
							<td class="centered">Akcja</td>
							<td class="centered">Administracja</td>
						</tr>
					</thead>
					<c:forEach items="${products}" var="product">
						<tr>
							<td>${product.name }</td>
							<td>${product.description}</td>
							<td>${product.price} PLN</td>
							<td class="centered"><a href="#">Zamów teraz</a></td>
							<td class="centered">
								<form action='<c:url value="/productRemove"/>' method="post">
									<input type="submit" value="Usuń"></input>
									<input type="hidden" name="id_product" value="${product.id}"></input>
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />										
								</form>
							</td>
						</tr>
					</c:forEach>
				</table>			
			</div>
		</div>
		
	<jsp:include page="footer.jsp"/>
	</body>
</html>