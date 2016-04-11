<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Pasajero Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Add a Pasajero
</h1>

<c:url var="addAction" value="/pasajero/add" ></c:url>
<a href="/ReservaVuelosMVCHibernate/">Pasajeros</a>
<a href="/ReservaVuelosMVCHibernate/reportes">Reporte</a>
<a href="/ReservaVuelosMVCHibernate/aviones">Aviones</a>
<a href="/ReservaVuelosMVCHibernate/reservas">Reservas</a>
<a href="/ReservaVuelosMVCHibernate/rutas">Rutas</a>
<a href="/ReservaVuelosMVCHibernate/vuelos">Vuelos</a>
<form:form action="${addAction}" commandName="pasajero">
<table>

	<c:if test="${pasajero.id != 0}">
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
			<form:label path="identificacion">
				<spring:message text="Identificacion"/>
			</form:label>
		</td>
		<td>
			<form:input path="identificacion" />
		</td> 
	</tr>
	<tr>
			<td>
			<form:label path="numeroTarjeta">
				<spring:message text="Numero Tarjeta"/>
			</form:label>
		</td>
		<td>
			<form:input path="numeroTarjeta"/>
		</td> 
	

	</tr>
	<tr>
		<td>
			<form:label path="nombreCompleto">
				<spring:message text="Nombre Completo"/>
			</form:label>
		</td>
		<td>
			<form:input path="nombreCompleto" />
		</td>
	</tr>
		<tr>
		<td>
			<form:label path="telefono">
				<spring:message text="Telefono"/>
			</form:label>
		</td>
		<td>
			<form:input path="telefono" />
		</td>
	</tr>
	
	<tr>
		<td>
			<form:label path="tipoIdentificacion">
				<spring:message text="Tipo Identificación"/>
			</form:label>
		</td>
		<td>
		
			<select id="tipoIdentificacionInput" name="tipoId">
			    
			    <c:forEach items="${tiposIdentificaciones}" var="ti">
			  
			            <option value="${ti.id}" selected>${ti}</option>
			        
			    </c:forEach>
			</select>
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
		<c:if test="${pasajero.id != 0}">
				<input type="submit"
					value="<spring:message text="Edit Pasajero"/>" />
		</c:if>
		<c:if test="${pasajero.id == 0}">
				<input type="submit" 
					value="<spring:message text="Add pasajero"/>" />
		</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>pasajeros List</h3>
<c:if test="${!empty listPasajeros}">
	<table class="tg">
	<tr>
		<th width="80">Pasajero ID</th>
		<th width="80">Tipo de identificacion</th>
		<th width="80">Pasajero número identificación</th>
		<th width="120">Pasajero Name</th>
		<th width="120">Numero Tarjeta</th>
		<th width="120">Telefono</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listPasajeros}" var="pasajero">
		<tr>
			<td>${pasajero.id}</td>
			<td>${identificacion}</td>
			<td>${pasajero.identificacion}</td>
			<td>${pasajero.nombreCompleto}</td>
			<td>${pasajero.numeroTarjeta}</td>
			<td>${pasajero.telefono}</td>
			<td><a href="<c:url value='/edit/${pasajero.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/remove/${pasajero.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>
