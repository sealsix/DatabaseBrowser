
#### Database browser slouzi pro CRUD spravu connection settings k databazim. Dale je moznost se pripojit k dane databazi a nacist informace o ni jako schemata, sloupce, tabulky, data.

Momentalne podporuje pouze databazi H2, podporovane databaze jsou dostupne v rest resource /supportedTypes.


* s dev aktivnim profilem se automaticky pusti H2 db i v serverovem modu 
a pres liquibase se vytvori tabulky a naplni sample daty:

```
java -jar  -Dspring.profiles.active=dev  DatabaseBrowser.jar
```
* pripadne druhou instanci je mozne pustit bez dev profilu s nastavenim pripojeni k databazi

```
java -jar DatabaseBrowser.jar --spring.datasource.url=jdbc:h2:tcp://localhost:9090/mem:testdb 
--spring.datasource.username=dbb --spring.datasource.password=dbb --server.port=8083
```

#### dulezite:
#### TODO list:
TODO: 
- opravit nefungujici pusteni spring boot testu pres maven
- zvetsit mnozstvi testu
- integracni testy - zkusit najit zpusob, jak kontrolovat, ze vytvorene runtime datasourcy se po volani metod 
korektne zaviraji
- pridat zakladni jednotkove testy
- pridani pagination tak aby rest kontrolery strankovaly - napr u nahledu dat muze byt velke mnozstvi vracenych dat
- zvazit cachovani vytvorenych runtime datasourcu, pripadne celych runtime servis - bylo by ale nutne zajistit ze pri konci behu aplikace, nebo pri cache evict
budou cachovane datasoursy korektne ukoncovany. Byla by potreba synchronizacni cache v pripade
multinodoveho prostredi
- vracet TO ze servis do prezentacni vrstvy a zrusit zavislosti na domenove entity v prezentacni vrstve
- DatasourceInfoRestController pri nevalidnim datasource config vratit lepsi hlasku
- u resource /{id}/tables/{tableName}/preview pridat parametr schema aby bylo mozne nacitat tabulku z jakehokoliv schematu
- dokumentace api pres Swagger
- projit pom a pro jistotu zbuildene jar na zbytecnosti a zmensit velikost jar. 82 MB je moc :)
- pridat releasovani napr pres maven release plugin
- smazani maven wrapperu
- bonusove ulohy :)

### TODO UPDATE 28.3.
databazovy index nad id 
misto beanutils pouzit nejaky nereflexivni framework jako mapstruct aby to bylo rychle






# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/maven-plugin/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#using-boot-devtools)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#boot-features-security)
* [JDBC API](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#boot-features-sql)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Liquibase Migration](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#howto-execute-liquibase-database-migrations-on-startup)
* [Validation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-validation)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#production-ready)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
* [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

