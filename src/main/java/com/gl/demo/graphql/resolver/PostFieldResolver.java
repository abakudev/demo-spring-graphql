package com.gl.demo.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.gl.demo.graphql.dto.AuthorDTO;
import com.gl.demo.graphql.dto.PostDTO;
import com.gl.demo.graphql.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostFieldResolver implements GraphQLResolver<PostDTO> {

    private final AuthorService authorService;

    @Autowired
    public PostFieldResolver(AuthorService authorService) {
        this.authorService = authorService;
    }

    public AuthorDTO getAuthor(PostDTO post) {
        return this.authorService.getAuthorById(post.getAuthorId());
    }

}
