package com.gl.demo.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.gl.demo.graphql.dto.PostDTO;
import com.gl.demo.graphql.service.PostService;
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
