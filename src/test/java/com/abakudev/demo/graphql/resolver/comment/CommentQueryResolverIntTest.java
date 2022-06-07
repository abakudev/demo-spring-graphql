package com.abakudev.demo.graphql.resolver.comment;


import com.abakudev.demo.graphql.TestApplication;
import com.abakudev.demo.graphql.utils.FileReaderUtil;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.json.JSONException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
@Order(5)
class CommentQueryResolverIntTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    void shouldAbleToGetCommentData() throws IOException, JSONException {

        GraphQLResponse graphQLResponse = this.graphQLTestTemplate
                .postForResource("request/comment-query.graphqls");

        assertTrue(graphQLResponse.isOk());
        assertEquals(FileReaderUtil.read("response/comment-query.json"),
                graphQLResponse.getRawResponse().getBody(), true);
    }

}
