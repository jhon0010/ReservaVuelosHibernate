<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Rutas Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Add Ruta
</h1>

<c:url var="addAction" value="/ruta/add" ></c:url>

<form:form action="${addAction}" commandName="ruta">
<table>

	<c:if test="${ruta.id != 0}">
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
			<form:label path="aeropuertoOrigen">
				<spring:message text="Aeropuerto Origen"/>
			</form:label>
		</td>
		<td>
		
			<select id="aeropuertoOrigen" name="aeropuertoOrigenId">
			    
			    <c:forEach items="${aeropuertos}" var="aeropuertoOrigen">
			  
			            <option value="${aeropuertoOrigen.id}" selected>${aeropuertoOrigen}</option>
			        
			    </c:forEach>
			</select>
		</td>
	</tr>
	
		<tr>
		<td>
			<form:label path="aeropuertoDestino">
				<spring:message text="Aeropuerto Destino"/>
			</form:label>
		</td>
		<td>
		
			<select id="aeropuertoDestino" name="aeropuertoDestinoId">
			    
			    <c:forEach items="${aeropuertos}" var="aeropuertoDestino">
			  
			            <option value="${aeropuertoDestino.id}" selected>${aeropuertoDestino}</option>
			        
			    </c:forEach>
			</select>
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
		<c:if test="${ruta.id != 0}">
				<input type="submit"
					value="<spring:message text="Edit Ruta"/>" />
		</c:if>
		<c:if test="${ruta.id == 0}">
				<input type="submit" 
					value="<spring:message text="Add Ruta"/>" />
		</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>Rutas List</h3>
<c:if test="${!empty listRutas}">
	<table class="tg">
	<tr>
		<th width="80">Ruta ID</th>
		<th width="80">Aeropuerto de origen</th>
		<th width="80">Aeropuerto de destino</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listRutas}" var="ruta">
		<tr>
			<td>${ruta.id}</td>
			<td>${ruta.aeropuertoOrigen}</td>
			<td>${ruta.aeropuertoDestino}</td>
			<td><a href="<c:url value='/ruta/edit/${ruta.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/ruta/remove/${ruta.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>
