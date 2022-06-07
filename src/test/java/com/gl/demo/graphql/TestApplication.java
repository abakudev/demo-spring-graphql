package com.gl.demo.graphql;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(GraphQlApp.class)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@Nested
public class TestApplication {

}
