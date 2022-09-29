<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  No scriptlets!!! 
	  See http://download.oracle.com/javaee/5/tutorial/doc/bnakc.html 
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="presentation.web.model.NewActivityModel" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/resources/app.css"> 
<title>SaudeGes: criar atividade</title>
</head>
<body>
<h2>Criar Atividade</h2>
<form action="criarAtividade" method="post">
	<div class="mandatory_field">
		<label for="especialidade">Especialidade:</label>
		<select name="especialidade">  
			<c:forEach var="especialidade" items="${model.especialidades}">
				<option value="${especialidade.nome}">${especialidade.nome}</option>
			</c:forEach> 
		</select>
   </div>
    <div class="mandatory_field">
    	<label for="designacao">Designação:</label> 
    	<input type="text" name="designacao" size="50" value="${model.designacao}"/> 
    </div>
    <div class="mandatory_field">
		<label for="regular">Regular?:</label> 
		<select name="regular">
		    <option value="true">Sim</option>
		    <option value="false">Não</option>
		</select> <br/>
    </div>
   <div class="mandatory_field">
   		<label for="nrsessoes">Nº de Sessões:</label> 
   		<input type="text" name="nrsessoes" value="${model.nrSessoes}"/>
   </div>
   <div class="mandatory_field">
   		<label for="preco">Preço:</label> 
   		<input type="text" name="preco" value="${model.preco}"/>
   </div>
   
   <div class="mandatory_field">
   		<label for="duracao">Duração:</label> 
   		<input type="text" name="duracao" value="${model.duracao}"/>
   </div>
   
   <div class="optional_field">
   		<label for="maxparticipantes">Nº máximo de participantes:</label> 
   		<input type="text" name="maxparticipantes" value="${model.maxParticipantes}"/>
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