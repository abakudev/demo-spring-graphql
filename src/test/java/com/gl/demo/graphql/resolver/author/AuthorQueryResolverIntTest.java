package com.gl.demo.graphql.resolver.author;

import com.gl.demo.graphql.TestApplication;
import com.gl.demo.graphql.utils.FileReaderUtil;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.json.JSONException;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
@Order(1)
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

    @Test
    public void shouldReturnPostCountForAuthor() throws IOException, JSONException {

        GraphQLResponse graphQLResponse = this.graphQLTestTemplate
                .postForResource("request/post-count-query.graphqls");
        assertTrue(graphQLResponse.isOk());
        assertEquals(FileReaderUtil.read("response/post-count-query.json"),
                graphQLResponse.getRawResponse().getBody(), true);

    }

}