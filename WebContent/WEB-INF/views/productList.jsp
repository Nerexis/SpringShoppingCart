<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<body>
		<jsp:include page="header.jsp"/>
		<jsp:include page="leftMenu.jsp"/>
	
		<div class="contentContainer">
			<div class="menuPanel">
				<h1>Nasza oferta :)</h1>
			</div>
			<div class="menuPanel">
				<table class="dataTable">
					<thead>
						<tr>
							<td>Nazwa</td>
							<td>Opis</td>
							<td class="centered">Ilość dostępna</td>
							<td>Cena</td>
							<td class="centered">Akcja</td>
							<security:authorize access="hasAnyRole('ADMIN')">
					        	<td class="centered">Administracja</td>
					        </security:authorize>
							
						</tr>
					</thead>
					<c:forEach items="${products}" var="product">
						<tr>
							<td>${product.name }</td>
							<td>${product.description}</td>
							<td class="centered">${product.quantity}</td>
							<td>${product.price} PLN</td>
							<td class="centered">
					              <form action='<c:url value="/cartEntryAdd"/>' method="post">
					                  <input type="number" name="quantity" 
					                       step="1" value="1" min="1" max='${product.quantity}' size="5" class="centered"></input>
                                      <input type="submit" class="link" value="Dodaj do koszyka"></input>
                                      <input type="hidden" name="idProduct" value="${product.id}"></input>
                                      <input type="hidden" name="${_csrf.parameterName}"
                                          value="${_csrf.token}" />                                       
                                  </form>
                            </td>
							<security:authorize access="hasAnyRole('ADMIN')">
								<td class="centered">
									<form action='<c:url value="/productRemove"/>' method="post">
										<input type="submit" class="link" value="Usuń"></input>
										<input type="hidden" name="idProduct" value="${product.id}"></input>										
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />										
									</form>
								</td>
							</security:authorize>
						</tr>
					</c:forEach>
				</table>			
			</div>
		</div>
		
	<jsp:include page="footer.jsp"/>
	</body>
</html>