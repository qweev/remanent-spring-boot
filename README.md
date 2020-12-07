# remanent-spring-boot

## Endpoints
```
startup page
http://localhost:8080/

reports
http://localhost:8080/raporty.html

heartbeat
http://localhost:8080/heartbeat

Reports

http://localhost:8080/remanent/rest/raport/backdoor/pdf/brutto/{nazwaPliku}

http://localhost:8080/remanent/rest/raport/backdoor/pdf/netto//{nazwaPliku}

http://localhost:8080/remanent/rest/raport/backdoor/excel/brutto/{sciezka}

http://localhost:8080/remanent/rest/raport/backdoor/excel/netto{sciezka}
```

## Tools
```
MySQL Adminer
http://localhost:8087
```


## Performance
```
Generation of report against 16k positions takes about 10 seconds
```

## Maven actions
```
mvn clean install
mvn clean install versions:display-dependency-updates versions:display-plugin-updates

Before running it/e2e make sure MySQL DB instance is up & running
In /src/main/resources/application.properties update login & password to database

mvn clean install -P it
mvn clean install -P it -e -X > log.log
mvn clean install -P e2e
```

## Requirements
```
JDK/JRE 8/10 only

```

## Linux 
### Development
```
In docker compose comment section with volumes. During maven build database will be populated on the fly
Copy ./database/init/sql.sql to root level as SQL for Windows and Linux differ much. Why? IHNI
In pom file change login & pass for database in properties
In /src/main/resources/application.properties change login and password


```

## Debugging
### Java
```
java - jar JAR.jar -Dcom.sun.management.jmxremote.port=3333 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false
```

### SQL
```
SELECT count(*) FROM `pozycje`
```
