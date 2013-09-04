<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>


	<table border="1">
			<tr>
				<td>Network IP from</td>
				<td>${networkEntry.startIpString}</td>
			</tr>
			<tr>
				<td>Network IP to</td>
				<td>${networkEntry.endIpString}</td>
			</tr>
			<tr>
				<td>Location country code</td>
				<td>${networkEntry.location.countryCode}</td>
			</tr>			

			<tr>
				<td>Location region</td>
				<td>${networkEntry.location.region}</td>
			</tr>	
			<tr>
				<td>Location city</td>
				<td>${networkEntry.location.city}</td>
			</tr>	
			<tr>
				<td>Location postal code</td>
				<td>${networkEntry.location.postalCode}</td>
			</tr>	
			<tr>
				<td>Location latitude</td>
				<td>${networkEntry.location.latitude}</td>
			</tr>	
			<tr>
				<td>Location longitude</td>
				<td>${networkEntry.location.longitude}</td>
			</tr>	
			<tr>
				<td>Location metro code</td>
				<td>${networkEntry.location.metroCode}</td>
			</tr>	
			<tr>
				<td>Location area code</td>
				<td>${networkEntry.location.areaCode}</td>
			</tr>							
	</table>	
</body>
</html>