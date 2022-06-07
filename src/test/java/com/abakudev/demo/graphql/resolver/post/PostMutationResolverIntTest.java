package com.abakudev.demo.graphql.resolver.post;

import com.abakudev.demo.graphql.TestApplication;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
@Order(4)
class PostMutationResolverIntTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    void shouldAbleToGetCreatePost() throws IOException {

        GraphQLResponse graphQLResponse = this.graphQLTestTemplate
                .postForResource("request/create-post-mutation.graphqls");
        String uuid = graphQLResponse.get("$.data.createPost");
        assertTrue(graphQLResponse.isOk());
        assertNotNull(uuid);
    }

}