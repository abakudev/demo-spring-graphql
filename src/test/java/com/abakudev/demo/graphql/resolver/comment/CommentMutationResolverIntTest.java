package com.abakudev.demo.graphql.resolver.comment;

import com.abakudev.demo.graphql.TestApplication;
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
@Order(6)
class CommentMutationResolverIntTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    void shouldAbleToGetCreateCommentOk() throws IOException {

        GraphQLResponse graphQLResponse = this.graphQLTestTemplate
                .postForResource("request/create-comment-mutation.graphqls");
        String uuid = graphQLResponse.get("$.data.createComment");

        assertTrue(graphQLResponse.isOk());
        assertNotNull(uuid);
    }

    @Test
    void shouldAbleToGetCreateCommentError() throws IOException {

        String errorMessageExpected = "Post is not exist with id: 023946ee-6173-4d49-8bac-c0b9e4a7ba88";
        GraphQLResponse graphQLResponse = this.graphQLTestTemplate
                .postForResource("request/create-comment-mutation-error.graphqls");
        String errorMessage = graphQLResponse.get("$.errors[0].message");

        assertTrue(graphQLResponse.isOk());
        assertEquals(errorMessageExpected, errorMessage);
    }

}
