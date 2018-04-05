<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Sklep Internetowy - Damian Winnicki</title>
	<link href="https://fonts.googleapis.com/css?family=Arvo|Bowlby+One+SC|Karla|Quicksand" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="resources/style.css" />
	
</head>

<div class="headerContainer">
	<h1>Sklep internetowy</h1>
	<div class="headerContent">
	    <div class="headerContentLeft">
			<a href="${pageContext.request.contextPath}/welcome">Strona główna</a>
			<a href="${pageContext.request.contextPath}/productList">Produkty</a>		
			<c:if test="${pageContext.request.userPrincipal.name != null}">		
				<a href="${pageContext.request.contextPath}/productAdd">Dodaj produkt</a>
		    </c:if>
		</div>
		<div class="headerContentRight">
		 <c:if test="${pageContext.request.userPrincipal.name != null}">
			<form method="post" action="${pageContext.request.contextPath}/logout" id="form-logout">
			<a href='<c:url value="/cartEntries"/>'>Mój koszyk</a>
		    <a href="#">
		         Zalogowano jako ${pageContext.request.userPrincipal.name}!</a>
				<button type="submit" class="link">Wyloguj</button>
			    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>         
			</form>			
		 </c:if>
		 <c:if test="${pageContext.request.userPrincipal.name == null}">
			    <a href="${pageContext.request.contextPath}/login">Login</a>
			<a href="${pageContext.request.contextPath}/register">Rejestracja</a>
		 </c:if>
        </div>
        <div class="clearBoth"></div>
	</div>
</div>

