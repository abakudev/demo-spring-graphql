package com.gl.demo.graphql.resolver.author;

import com.gl.demo.graphql.dto.AuthorDTO;
import com.gl.demo.graphql.dto.PostDTO;
import com.gl.demo.graphql.service.PostService;
import graphql.kickstart.tools.GraphQLResolver;
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

    public Integer postCount(AuthorDTO author) {
        return this.postService.getPostCountByAuthorId(author.getId());
    }

}
