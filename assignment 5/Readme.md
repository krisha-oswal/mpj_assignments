1. Download extensions:
   Java extension Pack
   tomcat by microsoft

2. Download tomcat https://tomcat.apache.org/download-90.cgi
   Core - tar.gz(pgp,sha512)

3. (base) krisha@kriiis-Laptop ~ % cd /Users/ananya/apache-tomcat-9.0.117/bin
   (base) krisha@kriiis-Laptop bin % chmod +x \*.sh
   (base) krisha@kriiis-Laptop bin % ./startup.sh
   Using CATALINA_BASE: /Users/kriii/apache-tomcat-9.0.117
   Using CATALINA_HOME: /Users/kriii/apache-tomcat-9.0.117
   Using CATALINA_TMPDIR: /Users/kriii/apache-tomcat-9.0.117/temp
   Using JRE_HOME: /opt/homebrew/opt/openjdk@17
   Using CLASSPATH: /Users/kriii/apache-tomcat-9.0.117/bin/bootstrap.jar:/Users/ananya/apache-tomcat-9.0.117/bin/tomcat-juli.jar
   Using CATALINA_OPTS:  
   Tomcat started.

---

./shutdown.sh
./startup.sh

4. http://localhost:8080

5. Donwload MySQL Connector https://dev.mysql.com/downloads/connector/j/

   Operating System: Platform Independent, ZIP Archive
   look for mysql-connector-j-8.0.xx.jar
   Put it in lib


6. make a folder names EmployeeApp in WebApps in TomCat

EmployeeApp/
 ├── index.jsp
 └── WEB-INF/
      ├── web.xml
      ├── classes/
      │    └── AddEmployeeServlet.class
      └── lib/
           └── mysql-connector-j-xxx.jar


index.jsp        - form for user input  
web.xml          - servlet configuration  
classes/         - compiled servlet (.class file)  
lib/             - MySQL JDBC driver  

7. Run addemployeeservlet

javac -cp ".:/Users/kriii/apache-tomcat-9.0.117/lib/servlet-api.jar" AddEmployeeServlet.java
ls

- move this into tomcat's WEB_INF classes folder

javac  → Java compiler (converts .java to .class)
-cp    → classpath (tells Java where required libraries are)
.      → current directory (where the .java file is)
:      → separator (used in Mac/Linux to separate paths)
/Users/.../servlet-api.jar → servlet library from Tomcat (needed for javax.servlet)
AddEmployeeServlet.java → source file to compile
Result → creates AddEmployeeServlet.class

ls → lists files in the folder to confirm .class file is created

8. SQL 

mysql> create database company;
Query OK, 1 row affected (0.00 sec)

mysql> use company;
Database changed
mysql> CREATE TABLE employee (
    ->     id INT AUTO_INCREMENT PRIMARY KEY,
    ->     name VARCHAR(50),
    ->     email VARCHAR(50),
    ->     salary DOUBLE
    -> );
Query OK, 0 rows affected (0.02 sec)

mysql> SHOW TABLES;
+-------------------+
| Tables_in_company |
+-------------------+
| employee          |
+-------------------+
1 row in set (0.00 sec)

mysql> select * from employee;
+----+--------+-------------+--------+
| id | name   | email       | salary |
+----+--------+-------------+--------+
|  1 | krisha | k@gmail.com |   1000 |
+----+--------+-------------+--------+
1 row in set (0.01 sec)


9. Terminal

cd /Users/kriii/apache-tomcat-9.0.117/bin
./shutdown.sh
./startup.sh

http://localhost:8080/EmployeeApp/addEmployee


addEmplyee comes from web.xml file
