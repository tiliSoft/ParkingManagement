/*************************************
*
* Sima Sotiraki
* Sotiraq.sima@gmail.com
* http://ww.simasware.com
*
**************************************/

1. Use Tomcat or JBoss to deploy the "ParkingManagement.war"
2. In your localhost MySQL DB create one new DB with the name "Parking302" and 
   add a user, with username "Parking302" and password "ParkingMGMT" and give full access to the DB "Pakring302"
3. Import "db_dump.sql" script into the new DB
4. Browse to the new Application like -> "http://localhost:8080/ParkingManagement"
5. Use the credential username: "root", password: "toor"



More Info:
a. You can change DB Name/User/Pass from the "web.xml"
b. You can change Application User/Pass from the DB
c. Java interface communicate with DB through the middleware "DBLibrary_ParkingManagement.jar". 
d. Source Code for the Java Interface available in the folder "ParkingManagement".
e. Source Code for the middleware available in the folder "DBLibrary_ParkingManagement".



feel free to contact me: http://ww.simasware.com


