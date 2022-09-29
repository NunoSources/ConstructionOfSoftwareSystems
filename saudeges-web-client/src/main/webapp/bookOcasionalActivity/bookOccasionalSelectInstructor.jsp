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
<form id="form" action="confirmarAgendarAtividadeOcasional" method="post">
	<label for="especialidade">Especialidade:</label>
	<input type="text" name="especialidade" value="${model.especialidade}" readonly ><br><br>
	
	<label for="designacao">Atividade:</label>
	<input type="text" name="designacao" value="${model.designacao}" readonly ><br><br>
	
	
     <div class="mandatory_field">
		<label for="sessoes">Sessoes:</label>
		<select name="sessoes" readonly multiple id="sessoes">  
			<c:forEach var="sessao" items="${model.sessoes}">
				<option value="${sessao}">${sessao}</option>
			</c:forEach> 
		</select>
   </div><br><br>
   
   <div class="mandatory_field">
		<label for="instrutor">Instrutor:</label>
		<select name="instrutor">  
			<c:forEach var="instr" items="${model.instrutores}">
				<option value="${instr.numero}">${instr.numero} - ${instr.nome}</option>
			</c:forEach> 
		</select>
   </div><br><br>
   
   <label for="email">Email:</label>
   <input type="text" name="email" value="${model.email}" ><br><br>
   
   
   
   <div class="button" align="right">
   		<input type="submit" value="Agendar Atividade Ocasional">
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

<script type="text/javascript">

	var sessoes = document.querySelector("#sessoes");
	var form = document.querySelector("#form");

	window.onload = function() {

		for (i = 0; i < sessoes.options.length; i++) {
			var optIns = document.createElement("input");
			optIns.setAttribute("type", "hidden");
		    optIns.setAttribute("value", sessoes.options[i].value);
		    optIns.setAttribute("name", "sessoesInseridas[]");
		    form.appendChild(optIns);
        }
		
	
	};
</script>
</html>