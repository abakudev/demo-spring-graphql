package com.abakudev.demo.graphql.resolver.comment;

import com.abakudev.demo.graphql.dto.AuthorDTO;
import com.abakudev.demo.graphql.dto.CommentDTO;
import com.abakudev.demo.graphql.dto.PostDTO;
import com.abakudev.demo.graphql.service.PostService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentFieldResolver implements GraphQLResolver<CommentDTO> {

    private final PostService postService;

    @Autowired
    public CommentFieldResolver(PostService postService) {
        this.postService = postService;
    }

    public PostDTO getPost(CommentDTO comment) {
        return this.postService.getPostById(comment.getPostId());
    }

    public AuthorDTO getAuthor(CommentDTO comment) {
        return this.postService.getAuthorById(comment.getAuthorId());
    }

}
