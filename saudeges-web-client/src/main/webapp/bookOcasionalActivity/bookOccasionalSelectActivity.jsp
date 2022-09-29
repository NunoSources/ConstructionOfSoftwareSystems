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
<form id="form" action="agendarAtividadeOcasionalSelecionarInstrutor" method="post">
	<label for="especialidade">Especialidade:</label>
	<input type="text" name="especialidade" value="${model.especialidade}" readonly ><br><br>
	
     <div class="mandatory_field">
		<label for="designacao">Atividade:</label>
		<select name="designacao">  
			<c:forEach var="at" items="${model.atividades}">
				<option value="${at.designacao}">${at.designacao} - ${at.preco} euros</option>
			</c:forEach> 
		</select>
   </div><br><br>
   
   <div style="height: 140px;">
	<label for="sessoes" style="float:left; padding-right:5px;">Sessões:</label>
	<select name="sessoes" id="sessoes" size="8" multiple style="min-width:250px;display:inline-block;float:left;" onchange="enableButton()"></select>
	<input type="hidden" name="insertedSessoes[]" id="sessoesInseridas" value="" />
	<input type="button" id="remover" value="Remover" style="display:inline; margin-left:30px; margin-top:20px;" disabled onclick="removeSessao()">
   </div><br><br>
   
   <hr>
   <h3>Criar Sessão:</h3>
   <label for="date">Data e Hora:</label>
	<input type="datetime-local" name="date" id="date"/><br>
	 <div class="button">
   		<input type="button" value="Criar Sessão" id="addSessao">
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
<script type="text/javascript">

	var sessoes = document.querySelector("#sessoes");
	var date = document.querySelector("#date");
	var btnAddSessao = document.querySelector("#addSessao");
	var form = document.querySelector("#form");
	
	btnAddSessao.addEventListener("click", addSessao);
	
	function addSessao(){
	 if(date.value !== ""){
	   // Don't build new HTML by concatenating strings. Create elements and configure them as objects
	   var opt = document.createElement("option");
	   var optIns = document.createElement("input");

	   var session = date.value;
	   opt.textContent = session;
	   opt.setAttribute("value", session);

	   optIns.setAttribute("type", "hidden");
	   optIns.setAttribute("value", session);
	   optIns.setAttribute("name", "sessoesInseridas[]");

	   sessoes.appendChild(opt);
	   form.appendChild(optIns);
	   // Only use hyperlinks for navigation, not to have something to click on. Any element can be clicked
	   //var span = document.createElement("span");
	   //span.classList.add("remove");
	   //span.textContent = "remove skill";
	  // span.addEventListener("click", removeSkill);  
	   //li.appendChild(span);  // Add the span to the bullet
	   //skillList.appendChild(li); // Add the bullet to the list
	   //newSkill.value = "";	
	 }
	}

	function enableButton()
	{
	    var selectelem = document.getElementById('sessoes');
	    var btnelem = document.getElementById('remover');
	    btnelem.disabled = !selectelem.value;
	}
				
	function removeSessao(){
		 for (i = 0; i < sessoes.options.length; i++)
	        {
	            if (sessoes.options[i].selected == true)
	            {
	                sessoes.remove(i);
	                var input = document.getElementsByName('sessoesInseridas[]');
	                form.removeChild(input[i]);
	                removeSessao();
	                break;
	            }
	        }

	      if (sessoes.options.length === 0) {
	    	  var btnelem = document.getElementById('remover');
	  	      btnelem.disabled = true;
		  }
	}
</script>
</html>