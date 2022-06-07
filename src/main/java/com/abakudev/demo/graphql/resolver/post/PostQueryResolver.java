package com.abakudev.demo.graphql.resolver.post;

import com.abakudev.demo.graphql.dto.PostDTO;
import com.abakudev.demo.graphql.service.PostService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostQueryResolver implements GraphQLQueryResolver {

    private final PostService postService;

    @Autowired
    public PostQueryResolver(PostService postService) {
        this.postService = postService;
    }

    public List<PostDTO> recentPosts(int count, int offset) {
        return this.postService.getRecentPosts(count, offset);
    }

}
