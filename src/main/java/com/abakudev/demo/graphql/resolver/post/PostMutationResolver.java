package com.abakudev.demo.graphql.resolver.post;

import com.abakudev.demo.graphql.dto.PostDTO;
import com.abakudev.demo.graphql.service.PostService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PostMutationResolver implements GraphQLMutationResolver {

    private final PostService postService;
    private final PostPublisher postPublisher;

    @Autowired
    public PostMutationResolver(PostService postService, PostPublisher postPublisher) {
        this.postService = postService;
        this.postPublisher = postPublisher;
    }

    public UUID createPost(PostDTO postDto) {

        UUID uuid = this.postService.createPost(postDto);
        postDto.setId(uuid);
        this.postPublisher.publish(postDto);
        return uuid;
    }

}