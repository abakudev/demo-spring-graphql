package com.abakudev.demo.graphql.resolver.post;

import com.abakudev.demo.graphql.dto.AuthorDTO;
import com.abakudev.demo.graphql.dto.CommentDTO;
import com.abakudev.demo.graphql.dto.PostDTO;
import com.abakudev.demo.graphql.service.AuthorService;
import com.abakudev.demo.graphql.service.CommentService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostFieldResolver implements GraphQLResolver<PostDTO> {

    private final AuthorService authorService;
    private final CommentService commentService;

    @Autowired
    public PostFieldResolver(AuthorService authorService, CommentService commentService) {
        this.authorService = authorService;
        this.commentService = commentService;
    }

    public AuthorDTO getAuthor(PostDTO post) {
        return this.authorService.getAuthorById(post.getAuthorId());
    }

    public List<CommentDTO> getComments(PostDTO post, Integer first) {
        return this.commentService.getFirstFewCommentsByPostId(post.getId(), first);
    }

}
