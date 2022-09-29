<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  No scriptlets!!! 
	  See http://download.oracle.com/javaee/5/tutorial/doc/bnakc.html 
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="presentation.web.model.DefineScheduleModel" scope="request"/>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Definir Horario</title>
</head>
<body>
<h2>Definir Horario</h2>
<form action="definirHorario" method="post" id="form">
	<div class="mandatory_field">
		<label for="designacao">Designacao da Atividade:</label>
		<select name="designacao">  
			<c:forEach var="atividade" items="${model.atividadesRegulares}">
				<option value="${atividade.designacao}">${atividade.designacao}</option>
			</c:forEach> 
		</select>
   </div><br>
   
   <div class="mandatory_field">
		<label for="instrutor">Instrutor:</label>
		<select name="instrutor">  
			<c:forEach var="instrutor" items="${model.instrutores}">
				<option value="${instrutor.numero}">${instrutor.nome}</option>
			</c:forEach> 
		</select>
   </div><br>
   
    <label for="startDate">Data Inicio:</label>
    <input type="date" name="startDate"  value="${model.startDate}" pattern="\d{2}-\d{2}-\d{4}"/>
   	<span class ="validity"></span><span style="margin-left: 20px;"/>
   
	<label for="duracao">Duração:</label>
	<input type="text" name="duracao" value="${model.duracao}"><br><br>
	
	<div style="height: 140px;">
	<label for="sessoes" style="float:left; padding-right:5px;">Sessões:</label>
	<select name="sessoes" id="sessoes" size="8" multiple style="min-width:250px;display:inline-block;float:left;" onchange="enableButton()"></select>
	<input type="hidden" name="insertedSessoes[]" id="sessoesInseridas" value="" />
	<input type="button" id="remover" value="Remover" style="display:inline; margin-left:30px; margin-top:20px;" disabled onclick="removeSessao()">
	</div>
	
	<hr>
	<h4>Criar Sessão</h4>
	
	<label for="diaSemana" style="padding-right:10px;">Dia da Semana:</label>
	<select name="diaSemana" id="diaSemana">
		<option value="1">Segunda</option>
		<option value="2">Terça</option>
		<option value="3">Quarta</option>
		<option value="4">Quinta</option>
		<option value="5">Sexta</option>
		<option value="6">Sabado</option>
		<option value="7">Domingo</option>
	</select><br>
	
	<label for="startHour">Hora de Inicio:</label>
	<input type="time" name="startHour" id="startHour" value="08:00" pattern="\d{2}:\d{2}"/>
	 <div class="button">
   		<input type="button" value="Criar Sessão" id="addSessao">
   	</div>
	  
   <div class="button" align="right">
   		<input type="submit" value="Definir Horario">
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
	var diaSemana = document.querySelector("#diaSemana");
	var startHour = document.querySelector("#startHour");
	var btnAddSessao = document.querySelector("#addSessao");
	var form = document.querySelector("#form");
	
	btnAddSessao.addEventListener("click", addSessao);
	
	function addSessao(){
	 if(diaSemana.value !== "" && startHour.value !== ""){
	   // Don't build new HTML by concatenating strings. Create elements and configure them as objects
	   var opt = document.createElement("option");
	   var optIns = document.createElement("input");

	   var session = diaSemana.value + " " + startHour.value;
	   opt.textContent = diaSemana.selectedOptions[0].text + " - " + startHour.value;
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

	   startHour.value = "08:00";
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
