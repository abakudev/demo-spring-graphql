### Build GraphQL API in SpringBoot

### GraphQL

[GraphQL](https://graphql.org/) is a query language to fetch data from APIs. GraphQL is developed by Facebook as an alternate of REST APIs. GraphQL solves some of the issues which arise when REST APIs evolves and scale.

- **GraphQL vs Rest**
  
  - Any REST endpoint generally returns a response in JSON in specific format. You either get a full JSON response or nothing at all. GraphQL provides ability to query API and get exactly what you need, nothing more and nothing less.
  
  - Any REST endpoint generally returns a response of specific Domain. Sometime clients need to call multiple REST endpoint to collect data. GraphQL provides ability to collect data in a single query.
  
  - You perform CRUD operations in REST using different HTTP verbs (GET, POST, PUT, DELETE etc.) whereas CRUD operations in GraphQL is performed using Query (or Mutation). Clients query the GraphQL API by making an HTTP POST request with Query (or Mutation) as Request Body.

### Terminology

Let’s have a look at GraphQL’s basic terminology.

- **Schema:** is a GraphQL schema which includes Query and Mutation
- **Query:** is a read operation requested to a GraphQL Server.
- **Mutation:** is a create, update and delete operations requested to GraphQL Server.
- **Resolver:** is responsible for mapping the Query (or Mutation) operation to backend service responsible to handle the request.
- **Fields:** are the properties of the GraphQL objects for e.g. User, Post and Comment etc.
- **Scalar:** is the type of the field for e.g. Int, Float, String, Boolean, ID. ID represents unique identifier, serialized as String.
- **Enum:** is a special kind of Scalar that is restricted to a particular set of allowed values.
- **Interface:** is an abstract type that includes a certain set of fields that a type must include to implement the interface.
- **Input:** is a GraphQL object passed, which is passed in Mutation operation such as create and update.

#### 

### Project Setup

##### Requirements

- Java 1.8 and Above
* Spring Boot Framework > 2.x.x (web)

##### Depedencies

**Gradle**

```
repositories {
    mavenCentral()
}

dependencies {
  // to turn spring boot application into GraphQL server
  implementation 'com.graphql-java-kickstart:graphql-spring-boot-starter:11.0.0'

  // to embed Altair tool for schema introspection and query debugging
  runtimeOnly 'com.graphql-java-kickstart:altair-spring-boot-starter:11.0.0'

  // to embed GraphiQL tool for schema introspection and query debugging
  runtimeOnly 'com.graphql-java-kickstart:graphiql-spring-boot-starter:11.0.0'

  // to embed GraphQL Playground tool for schema introspection and query debugging
  runtimeOnly 'com.graphql-java-kickstart:playground-spring-boot-starter:11.0.0'

  // to embed Voyager tool for visually explore GraphQL APIs as an interactive graph 
  runtimeOnly 'com.graphql-java-kickstart:voyager-spring-boot-starter:11.0.0'

  // testing facilities
  testImplementation 'com.graphql-java-kickstart:graphql-spring-boot-starter-test:11.0.0'
}
```

**Maven**

```
<dependency>
    <groupId>com.graphql-java-kickstart</groupId>
    <artifactId>graphql-spring-boot-starter</artifactId>
    <version>11.0.0</version>
</dependency>

<!-- to embed Altair tool -->
<dependency>
    <groupId>com.graphql-java-kickstart</groupId>
    <artifactId>altair-spring-boot-starter</artifactId>
    <version>11.0.0</version>
    <scope>runtime</scope>
</dependency>

<!-- to embed GraphiQL tool -->
<dependency>
    <groupId>com.graphql-java-kickstart</groupId>
    <artifactId>graphiql-spring-boot-starter</artifactId>
    <version>11.0.0</version>
    <scope>runtime</scope>
</dependency>

<!-- to embed GraphQL Playground tool -->
<dependency>
    <groupId>com.graphql-java-kickstart</groupId>
    <artifactId>playground-spring-boot-starter</artifactId>
    <version>11.0.0</version>
    <scope>runtime</scope>
</dependency>

<!-- to embed Voyager tool -->
<dependency>
    <groupId>com.graphql-java-kickstart</groupId>
    <artifactId>voyager-spring-boot-starter</artifactId>
    <version>11.0.0</version>
    <scope>runtime</scope>
</dependency>

<!-- testing facilities -->
<dependency>
    <groupId>com.graphql-java-kickstart</groupId>
    <artifactId>graphql-spring-boot-starter-test</artifactId>
    <version>11.0.0</version>
    <scope>test</scope>
</dependency>
```

#### Writing the Schema

#### Implement a Queries

#### Implement Mutations

#### Implement Subscriptions

#### Error Handling

#### Testing
