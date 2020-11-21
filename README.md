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
mvn clean install -P it
mvn clean install -P it -e -X > log.log
```