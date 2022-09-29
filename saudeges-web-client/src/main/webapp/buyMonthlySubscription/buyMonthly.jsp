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
<form action="confirmarCompraParticipacaoMensal" method="post">
	
	<label for="designacao">Atividade:</label>
	<input type="text" name="designacao" value="${model.designacao}" readonly ><br><br>
	
	<label for="preco">Preço (Mensal):</label>
	<input type="text" name="preco" value="${model.preco}" readonly ><br><br>
	
   <div class="mandatory_field">
		<label for="horario">Horarios Disponiveis:</label> 
		<select name="horario" style="width: 200px;">
		    <c:forEach var="horario" items="${model.horario}">
				<option value="${horario.id}">${horario.sessoes}</option>
			</c:forEach> 
		</select> <br/>
    </div> <br/> 

    <label for="startDate">Data Inicio:</label>
    <input type="date" name="startDate"  value="${model.startDate}" pattern="\d{2}-\d{2}-\d{4}"/>
   	<span class ="validity"></span><span style="margin-left: 20px;"/>
   
	<label for="duracao">Duração:</label>
	<input type="text" name="duracao" value="${model.duracao}"><br><br>
   
   <div class="mandatory_field">
   		<label for="email">Email:</label> 
   		<input type="text" name="email" value="${model.email}"/>
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