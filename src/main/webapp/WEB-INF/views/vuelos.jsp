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

<c:url var="addAction" value="/vuelo/add" ></c:url>
<a href="/ReservaVuelosMVCHibernate/">Pasajeros</a>
<a href="/ReservaVuelosMVCHibernate/reportes">Reporte</a>
<a href="/ReservaVuelosMVCHibernate/aviones">Aviones</a>
<a href="/ReservaVuelosMVCHibernate/reservas">Reservas</a>
<a href="/ReservaVuelosMVCHibernate/rutas">Rutas</a>
<a href="/ReservaVuelosMVCHibernate/vuelos">Vuelos</a>
<form:form action="${addAction}" commandName="vuelo">

<h1 style="color:red;">${error}</h1>

<table>

	<c:if test="${vuelo.id != 0}">
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
			<label>
				<spring:message text="Fecha Salida (yyyy-MM-dd HH:mm:ss)"/>
			</label>
		</td>
		<td>
			<input value="${fechaVuelo}" name="fechaVuelo"></input>
		</td> 
	</tr>
	
			<tr>
		<td>
			<label>
				<spring:message text="Fecha Arrivo (yyyy-MM-dd HH:mm:ss)"/>
			</label>
		</td>
		<td>
			<input value="${fechaArrivo}" name="fechaArrivo"></input>
		</td> 
	</tr>
	
	<tr>
		<td>
			<form:label path="rutaCumplir">
				<spring:message text="Ruta a Cumplir"/>
			</form:label>
		</td>
		<td>		
			<select id="rutaCumplir" name="rutaCumplirId">
			    <c:forEach items="${rutas}" var="rutaCumplir">
			            <option value="${rutaCumplir.id}" selected>${rutaCumplir}</option>			        
			    </c:forEach>
			</select>
		</td>
	</tr>
	
		<tr>
		<td>
			<form:label path="avion">
				<spring:message text="Avion"/>
			</form:label>
		</td>
		<td>		
			<select id="avion" name="avionId">			    
			    <c:forEach items="${aviones}" var="avion">			  
			            <option value="${avion.id}" selected>${avion}</option>
			    </c:forEach>
			</select>
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
<h3>Vuelos List</h3>
<c:if test="${!empty listVuelos}">
	<table class="tg">
	<tr>
		<th width="80">Vuelo ID</th>
		<th width="80">Ruta a cumplir</th>
		<th width="80">Avión</th>
		<th width="80">Fecha y hora salida</th>
		<th width="80">Fecha y hora llegada</th>

		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listVuelos}" var="vuelo">
		<tr>
			<td>${vuelo.id}</td>
			<td>${vuelo.rutaCumplir}</td>
			<td>${vuelo.avion}</td>
			<td>${vuelo.fechaSalida}</td>
			<td>${vuelo.fechaArriboEstimada}</td>
			<td><a href="<c:url value='/vuelo/edit/${vuelo.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/vuelo/remove/${vuelo.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>
