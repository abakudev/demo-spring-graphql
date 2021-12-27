package com.gl.demo.graphql.resolver.author;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.gl.demo.graphql.dto.AuthorDto;
import com.gl.demo.graphql.service.AuthorService;
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

    public UUID createAuthor(AuthorDto authorDto){
        return this.authorService.createAuthor(authorDto);
    }

}
