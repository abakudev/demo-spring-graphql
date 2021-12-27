package com.gl.demo.graphql.resolver.post;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.gl.demo.graphql.dto.PostDTO;
import com.gl.demo.graphql.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PostMutationResolver implements GraphQLMutationResolver {

    private final PostService postService;

    @Autowired
    public PostMutationResolver(PostService postService) {
        this.postService = postService;
    }

    public UUID createPost(PostDTO post) {
        return this.postService.createPost(post);
    }

}
