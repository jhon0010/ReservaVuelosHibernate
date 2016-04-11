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
	Reporte vuelos y pasajeros por ruta
</h1>

<c:url var="addAction" value="/reporte/add" ></c:url>
<a href="/ReservaVuelosMVCHibernate/">Pasajeros</a>
<a href="/ReservaVuelosMVCHibernate/reportes">Reporte</a>
<a href="/ReservaVuelosMVCHibernate/aviones">Aviones</a>
<a href="/ReservaVuelosMVCHibernate/reservas">Reservas</a>
<a href="/ReservaVuelosMVCHibernate/rutas">Rutas</a>
<a href="/ReservaVuelosMVCHibernate/vuelos">Vuelos</a>


<form:form  action="${addAction}" >

<h1 style="color:red;">${error}</h1>

<table>

		
		<tr>
		<td>
			<label>
				<spring:message text="Avion"/>
			</label>
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
		<td colspan="2">
				<input type="submit"
					value="<spring:message text="Ver Reporte"/>" />
	</tr>
</table>	
</form:form>
<br>
<br>
<h3>Vuelos List</h3>
<c:if test="${!empty listReport}">
	<table class="tg">
	<tr>
		<th width="80">Ruta</th>
		<th width="80">Cantidad de vuelos</th>
		<th width="80">Cantidad pasajeros</th>
	</tr>
	<c:forEach items="${listReport}" var="report">
		<tr>
			<td>${report.ruta}</td>
			<td>${report.cantidadVuelos}</td>
			<td>${report.cantidadPasajeros}</td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>
