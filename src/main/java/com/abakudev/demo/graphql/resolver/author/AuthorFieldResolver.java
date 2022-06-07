package com.abakudev.demo.graphql.resolver.author;

import com.abakudev.demo.graphql.dto.AuthorDTO;
import com.abakudev.demo.graphql.dto.CommentDTO;
import com.abakudev.demo.graphql.dto.PostDTO;
import com.abakudev.demo.graphql.service.CommentService;
import com.abakudev.demo.graphql.service.PostService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorFieldResolver implements GraphQLResolver<AuthorDTO> {

    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public AuthorFieldResolver(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    public List<PostDTO> posts(AuthorDTO author) {
        return this.postService.getPostsByAuthorId(author.getId());
    }

    public Integer postCount(AuthorDTO author) {
        return this.postService.getPostCountByAuthorId(author.getId());
    }

    public List<CommentDTO> getComments (AuthorDTO author, Integer first) {
        return this.commentService.getFirstFewCommentsByAuthorId(author.getId(), first);
    }

}
