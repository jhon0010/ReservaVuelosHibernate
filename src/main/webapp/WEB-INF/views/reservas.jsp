<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Vuelos Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Add Vuelos
</h1>

<c:url var="addAction" value="/reserva/add" ></c:url>
<a href="/ReservaVuelosMVCHibernate/">Pasajeros</a>
<a href="/ReservaVuelosMVCHibernate/reportes">Reporte</a>
<a href="/ReservaVuelosMVCHibernate/aviones">Aviones</a>
<a href="/ReservaVuelosMVCHibernate/reservas">Reservas</a>
<a href="/ReservaVuelosMVCHibernate/rutas">Rutas</a>
<a href="/ReservaVuelosMVCHibernate/vuelos">Vuelos</a>
<form:form action="${addAction}" commandName="reserva">

<h1 style="color:red;">${error}</h1>

<table>

	<c:if test="${reserva.id != 0}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td> 
	</tr>
	</c:if>
	

	
	<tr>
		<td>
			<form:label path="pasajero">
				<spring:message text="Pasajero"/>
			</form:label>
		</td>
		<td>		
			<select id="pasajero" name="pasajeroId">
			    <c:forEach items="${pasajeros}" var="pasajero">
			            <option value="${pasajero.id}" selected>${pasajero}</option>			        
			    </c:forEach>
			</select>
		</td>
	</tr>
	
		<tr>
		<td>
			<form:label path="vuelo">
				<spring:message text="Vuelo"/>
			</form:label>
		</td>
		<td>		
			<select id="vuelo" name="vueloId">			    
			    <c:forEach items="${vuelos}" var="vuelo">			  
			            <option value="${vuelo.id}" selected>${vuelo}</option>
			    </c:forEach>
			</select>
		</td>
	</tr>
	
		<tr>
		<td>
			<form:label path="asientosReservados">
				<spring:message text="Número de Asientos a Reservar"/>
			</form:label>
		</td>
		<td>
			<form:input  path="asientosReservados" />
		</td> 
	</tr>
	
	<tr>
		<td colspan="2">
		<c:if test="${vuelo.id != 0}">
				<input type="submit"
					value="<spring:message text="Edit Vuelo"/>" />
		</c:if>
		<c:if test="${vuelo.id == 0}">
				<input type="submit" 
					value="<spring:message text="Add Vuelo"/>" />
		</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>Reservas List</h3>
<c:if test="${!empty listReservas}">
	<table class="tg">
	<tr>
		<th width="80">Rserva ID</th>
		<th width="80">Vuelo</th>
		<th width="80">Pasajero</th>
		<th width="80">Cantidad de asientos</th>

		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listReservas}" var="reserva">
		<tr>
			<td>${reserva.id}</td>
			<td>${reserva.vuelo}</td>
			<td>${reserva.pasajero}</td>
			<td>${reserva.asientosReservados}</td>
			<td><a href="<c:url value='/vuelo/edit/${reserva.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/vuelo/remove/${reserva.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>
