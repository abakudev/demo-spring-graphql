package com.gl.demo.graphql.resolver.author;

import com.gl.demo.graphql.TestApplication;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
@Order(2)
class AuthorMutationResolverIntTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    void shouldAbleToGetCreateAuthor() throws IOException {

        GraphQLResponse graphQLResponse = this.graphQLTestTemplate
                .postForResource("request/create-author-mutation.graphqls");
        String uuid = graphQLResponse.get("$.data.createAuthor");
        assertTrue(graphQLResponse.isOk());
        assertNotNull(uuid);
    }

    @Test
    void shouldAbleToGetCreateCommentError() throws IOException {

        String errorMessageExpected = "A author already exists with this email: john@gmail.com";
        GraphQLResponse graphQLResponse = this.graphQLTestTemplate
                .postForResource("request/create-author-mutation-error.graphqls");
        String errorMessage = graphQLResponse.get("$.errors[0].message");

        assertTrue(graphQLResponse.isOk());
        assertEquals(errorMessageExpected, errorMessage);
    }

}