# GraphQL in Spring Boot

A proof of concept to create a API GraphQL in Spring Boot.

#### Requirements

* [Java 11](https://www.oracle.com/java/technologies/downloads/) or later
* [Maven 3.2+](https://maven.apache.org/download.cgi)

#### Run Project

```
mvn clean install
```

```
mvn spring-boot:run
```

#### Run SonarQube

- Container of Sonarqube run in local is required.

```
mvn clean compile test jacoco:report sonar:sonar
```

#### Documentation

Document on How to build a GraphQL API in Spring Boot. [Documentation.md](https://github.com/abakudev/demo-spring-graphql/blob/main/Documentation.md)
