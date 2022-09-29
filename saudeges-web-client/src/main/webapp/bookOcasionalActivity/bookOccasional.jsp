<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  No scriptlets!!! 
	  See http://download.oracle.com/javaee/5/tutorial/doc/bnakc.html 
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="presentation.web.model.BookOccasionalModel" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/resources/app.css"> 
<title>SaudeGes: agendar ocasional</title>
</head>
<body>
<h2>Agendar Atividade Ocasional</h2>
<form action="agendarAtividadeOcasionalSelecionarAtividade" method="post">
	<div class="mandatory_field">
		<label for="especialidade">Especialidade:</label>
		<select name="especialidade">  
			<c:forEach var="especialidade" items="${model.especialidades}">
				<option value="${especialidade.nome}">${especialidade.nome}</option>
			</c:forEach> 
		</select>
   </div>
   
   <div class="button" align="right">
   		<input type="submit" value="Criar Atividade">
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