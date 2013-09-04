<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>geoip mongodb</title>
    </head>
    <body>
        <h1>Check location by ip (ipv4)</h1>

        <a href="/geoipmongo/network/find?ip=<%= request.getRemoteAddr()%>"> <%= request.getRemoteAddr()%> </a>           
        <br/>
        This product includes GeoLite data created by MaxMind, available from
        <a href="http://www.maxmind.com">http://www.maxmind.com</a>.
    </body>
</html>
