package com.abakudev.demo.graphql.resolver.comment;

import com.abakudev.demo.graphql.dto.CommentDTO;
import com.abakudev.demo.graphql.service.CommentService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommentMutationResolver implements GraphQLMutationResolver {

    private final CommentService commentService;
    private final CommentPublisher commentPublisher;

    @Autowired
    public CommentMutationResolver(CommentService commentService, CommentPublisher commentPublisher) {
        this.commentService = commentService;
        this.commentPublisher = commentPublisher;
    }

    public UUID createComment(CommentDTO commentDTO) {
        UUID id = this.commentService.createComment(commentDTO);
        commentDTO.setId(id);
        this.commentPublisher.publish(commentDTO);
        return id;
    }

}
