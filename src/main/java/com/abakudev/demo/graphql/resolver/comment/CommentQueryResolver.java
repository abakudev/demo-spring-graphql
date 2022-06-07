package com.abakudev.demo.graphql.resolver.comment;

import com.abakudev.demo.graphql.dto.CommentDTO;
import com.abakudev.demo.graphql.service.CommentService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentQueryResolver implements GraphQLQueryResolver {

    private final CommentService commentService;

    @Autowired
    public CommentQueryResolver(CommentService commentService) {
        this.commentService = commentService;
    }

    public List<CommentDTO> getComments(Integer count, Integer offset) {
            return this.commentService.getComments(count, offset);
    }

}
