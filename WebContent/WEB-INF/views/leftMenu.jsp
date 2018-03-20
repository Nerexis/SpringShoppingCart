<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="leftMenu">
	<div class="menuPanel">
		<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h3>Twój koszyk</h3>
		<ul>
			<li>TODO</li>
		</ul>
		</c:if>
		<c:if test="${pageContext.request.userPrincipal.name == null}">
			<p>Zaloguj się by dodać produkty do koszyka :)</p>
		</c:if>

	</div>
</div>