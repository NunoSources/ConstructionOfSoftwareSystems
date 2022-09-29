<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  No scriptlets!!! 
	  See http://download.oracle.com/javaee/5/tutorial/doc/bnakc.html 
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="presentation.web.model.MonthlySubModel" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/resources/app.css"> 
<title>SaudeGes: participacao mensal</title>
</head>
<body>
<h2>Comprar Participação Mensal em Atividade Regular</h2>
<form action="comprarParticipacaoMensal" method="post">
	<div class="mandatory_field">
		<label for="designacao">Atividade:</label>
		<select name="designacao">  
			<c:forEach var="atividade" items="${model.atividades}">
				<option value="${atividade.designacao}">${atividade.designacao}</option>
			</c:forEach> 
		</select>
   </div>
   
   <div class="button" align="right">
   		<input type="submit" value="Agendar Participação Mensal">
   </div>
</form>
<c:if test="${model.hasMessages}">
	<p>Mensagens</p>
	<ul>
	<c:forEach var="mensagem" items="${model.messages}">
		<li>${mensagem} 
	</c:forEach>
	</ul>
</c:if>

<br><br>
<a href="<c:url value="/index.html" />">Menu Inicial</a>
</body>
</html>