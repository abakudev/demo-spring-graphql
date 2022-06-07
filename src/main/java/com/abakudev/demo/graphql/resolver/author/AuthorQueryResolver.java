package com.abakudev.demo.graphql.resolver.author;

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
