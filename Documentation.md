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

### **Setting up the Service**

##### Requirements

- Java 11 and Above
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

#### 

#### **GraphQL Schemas**

The GraphQL server exposes a schema describing the API. This scheme is made up of type definitions. Each type has one or more fields, which each take zero or more arguments and return a specific type.

The graph is made up from the way these fields are nested with each other. Note that there is no need for the graph to be acyclic – cycles are perfectly acceptable – but it is directed. That is, the client can get from one field to its children, but it can't automatically get back to the parent unless the schema defines this explicitly.

An example GraphQL Schema for a blog may contain the following definitions, describing a Post, an Author of the post and a root query to get the most recent posts on the blog.

```
type Post {
    id: ID!
    title: String!
    text: String!
    category: String
    author: Author!
}

type Author {
    id: ID!
    name: String!
    thumbnail: String
    posts: [Post]!
}

# The Root Query for the application
type Query {
    recentPosts(count: Int, offset: Int): [Post]!
}

# The Root Mutation for the application
type Mutation {
    writePost(title: String!, text: String!, category: String) : Post!
}
```

The “!” at the end of some names indicates that this is a non-nullable type. Any type that does not have this can be null in the response from the server. The GraphQL service handles these correctly, allowing us to request child fields of nullable types safely.

The GraphQL Service also exposes the schema itself using a standard set of fields, allowing any client to query for the schema definition ahead of time.

This can allow for the client to automatically detect when the schema changes, and to allow for clients that dynamically adapt to the way that the schema works. One incredibly useful example of this is the GraphiQL tool – discussed later – that allows for us to interact with any GraphQL API.

#### **Writing the Schema**

The GraphQL Tools library works by processing GraphQL Schema files to build the correct structure and then wires special beans to this structure. **The Spring Boot GraphQL starter automatically finds these schema files**.

These files need to be saved with the extension “.*graphqls*” and can be present anywhere on the classpath. We can also have as many of these files as desired, so we can split the scheme up into modules as desired.

#### Implement a Queries

```
type Query {
    authors: [Author]!
}
```

```
import com.abakudev.demo.graphql.dto.AuthorDTO;
import com.abakudev.demo.graphql.service.AuthorService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorQueryResolver implements GraphQLQueryResolver {

    private final AuthorService authorService;

    @Autowired
    public AuthorQueryResolver(AuthorService authorService) {
        this.authorService = authorService;
    }

    public List<AuthorDTO> getAuthors(){
        return this.authorService.getAuthors();
    }

}
```

#### Implement Mutations

```
type Mutation {
    createAuthor(author: AuthorInput): ID!
}
```

```
import com.abakudev.demo.graphql.dto.AuthorDTO;
import com.abakudev.demo.graphql.service.AuthorService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthorMutationResolver implements GraphQLMutationResolver {

    private final AuthorService authorService;

    @Autowired
    public AuthorMutationResolver(AuthorService authorService) {
        this.authorService = authorService;
    }

    public UUID createAuthor(AuthorDTO author){
        return this.authorService.createAuthor(author);
    }

}
```

#### Implement Subscriptions

```
type Subscription {
    recentPost: Post!
    recentPostByAuthor(authorId: ID!): Post!
}
```

```
import com.abakudev.demo.graphql.dto.PostDTO;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PostSubscriptionResolver implements GraphQLSubscriptionResolver {

    private final PostPublisher postPublisher;

    @Autowired
    public PostSubscriptionResolver(PostPublisher postPublisher) {
        this.postPublisher = postPublisher;
    }

    public Publisher<PostDTO> recentPost() {
        return this.postPublisher.getRecentPost();
    }

    public Publisher<PostDTO> recentPostByAuthor(UUID authorId) {
        return this.postPublisher.getRecentPostByAuthorId(authorId);
    }

}
```

#### Error Handling

```
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomGraphErrorHandler implements GraphQLErrorHandler {

    @Override
    public boolean errorsPresent(List<GraphQLError> errors) {
        return GraphQLErrorHandler.super.errorsPresent(errors);
    }

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        return errors.stream().map(this::getNested).collect(Collectors.toList());
    }

    private GraphQLError getNested(GraphQLError graphQLError) {
        if(graphQLError instanceof ExceptionWhileDataFetching) {
            ExceptionWhileDataFetching ex = (ExceptionWhileDataFetching) graphQLError;
            if(ex.getException() instanceof GraphQLError){
                return (GraphQLError) ex.getException();
            }
        }
        return graphQLError;
    }
}
```


#### Testing
```
import com.abakudev.demo.graphql.TestApplication;
import com.abakudev.demo.graphql.utils.FileReaderUtil;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
class AuthorQueryResolverIntTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    void shouldAbleToGetAuthorData() throws IOException, JSONException {

        GraphQLResponse graphQLResponse = this.graphQLTestTemplate
                .postForResource("request/author-query.graphqls");
        assertTrue(graphQLResponse.isOk());
        assertEquals(FileReaderUtil.read("response/author-query.json"),
                graphQLResponse.getRawResponse().getBody(), true);
    }

}
```

```
import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileReaderUtil {

    public static String read (String location) throws IOException {
        return IOUtils.toString(
                new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8
        );
    }

}
```

```
query {
  authors {
    id
    name
    email
    posts {
      id
      title
    }
  }
}

"request/author-query.graphqls"
```
```
{
  "data": {
    "authors": [
      {
        "id": "b8addf5e-43f8-4165-9a5f-ab73094a5b1e",
        "name": "Peter",
        "email": "peteer@gmail.com",
        "posts": [
          {
            "id": "8144f608-3e87-47ea-ad16-677d5679b445",
            "title": "post title"
          }
        ]
      },
      {
        "id": "bffc75ba-c8e0-4178-93c9-20eb6cafed75",
        "name": "Smith",
        "email": "smith@gmail.com",
        "posts": [
          {
            "id": "883b8a41-831f-4a1a-86ca-d26adc8f84aa",
            "title": "Spring Boot"
          }
        ]
      },
      {
        "id": "44b0ed8f-351c-4368-a472-cadd17444947",
        "name": "John",
        "email": "john@gmail.com",
        "posts": [
          {
            "id": "6c80180c-51de-4f56-81c4-da0a8fbfa792",
            "title": "REACT"
          },
          {
            "id": "7e759e91-a0d4-469b-ad81-50ba3969d211",
            "title": "JS"
          }
        ]
      },
      {
        "id": "023946ee-6173-4d49-8bac-c0b9e4a7ba88",
        "name": "Shane",
        "email": "shane@gmail.com",
        "posts": []
      }
    ]
  }
}

"response/author-query.json"
```
