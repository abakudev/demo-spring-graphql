package com.gl.demo.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldQueryResolver implements GraphQLQueryResolver {

    private static final String HELLO_WORLD_MSG = "Hello World! GraphQL is awesome";

    public String getHelloWorld(){
        return HELLO_WORLD_MSG;
    }


}
