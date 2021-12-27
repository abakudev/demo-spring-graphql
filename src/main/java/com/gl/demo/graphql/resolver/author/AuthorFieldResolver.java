package com.gl.demo.graphql.resolver.author;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.gl.demo.graphql.dto.AuthorDTO;
import com.gl.demo.graphql.dto.PostDTO;
import com.gl.demo.graphql.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorFieldResolver implements GraphQLResolver<AuthorDTO> {

    private final PostService postService;

    @Autowired
    public AuthorFieldResolver(PostService postService) {
        this.postService = postService;
    }

    public List<PostDTO> posts(AuthorDTO author) {
        return this.postService.getPostsByAuthorId(author.getId());
    }

}
