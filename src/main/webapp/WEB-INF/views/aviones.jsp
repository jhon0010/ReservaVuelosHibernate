<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>AViones</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Add a Avion
</h1>

<c:url var="addAction" value="/avion/add" ></c:url>
<a href="/ReservaVuelosMVCHibernate/">Pasajeros</a>
<a href="/ReservaVuelosMVCHibernate/reportes">Reporte</a>
<a href="/ReservaVuelosMVCHibernate/aviones">Aviones</a>
<a href="/ReservaVuelosMVCHibernate/reservas">Reservas</a>
<a href="/ReservaVuelosMVCHibernate/rutas">Rutas</a>
<a href="/ReservaVuelosMVCHibernate/vuelos">Vuelos</a>
<form:form action="${addAction}" commandName="avion">
<table>

	<c:if test="${avion.id != 0}">
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
			<form:label path="fabricante">
				<spring:message text="Fabricante"/>
			</form:label>
		</td>
		<td>
			<form:input path="fabricante" />
		</td> 
	</tr>
	<tr>
			<td>
			<form:label path="modelo">
				<spring:message text="Modelo de avión"/>
			</form:label>
		</td>
		<td>
			<form:input path="modelo"/>
		</td> 
	</tr>
		<tr>
			<td>
			<form:label path="capacidad">
				<spring:message text="Capacidad de pasajeros del avión"/>
			</form:label>
		</td>
		<td>
			<form:input path="capacidad"/>
		</td> 
	</tr>
	<tr>
		<td colspan="2">
		<c:if test="${avion.id != 0}">
				<input type="submit"
					value="<spring:message text="Edit avion"/>" />
		</c:if>
		<c:if test="${avion.id == 0}">
				<input type="submit" 
					value="<spring:message text="Add avion"/>" />
		</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>Aviones List</h3>
<c:if test="${!empty listAviones}">
	<table class="tg">
	<tr>
		<th width="80">Avion ID</th>
		<th width="80">Modelo</th>
		<th width="80">Capacidad pasajeros</th>
		<th width="120">Fabricante</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listAviones}" var="avion">
		<tr>
			<td>${avion.id}</td>
			<td>${avion.modelo}</td>
			<td>${avion.capacidad}</td>
			<td>${avion.fabricante}</td>
			<td><a href="<c:url value='/avion/edit/${avion.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/avion/remove/${avion.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>
