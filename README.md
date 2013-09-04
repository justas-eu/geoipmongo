geoipmongo
===========

This application is a redeveloped [geoip](https://github.com/justas-eu/geoip "geoip")  version on MongoDB and Spring Data. Check
[demo](http://tomcat.justas.eu:888/geoipmongo/ "demo") 

The application tells ip address geo location.
The app is capable of importing maxmind data files in csv format.
The location table contains ~0.5 million records and network ~2 millions

Technologies
===========
- Spring 3.2.4
- Spring Data 1.2.1
- MongoDB
- OpenCSV

Prerequisites
===========
- Java 6 or 7
- Maven 3
- Tomcat 7 or other compatible servlet container
- In use with http://dev.maxmind.com/geoip/legacy/geolite/

Install
===========
<pre>
mvn install
</pre>


