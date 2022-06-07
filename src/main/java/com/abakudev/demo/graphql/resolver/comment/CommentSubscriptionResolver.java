package com.abakudev.demo.graphql.resolver.comment;

import com.abakudev.demo.graphql.dto.CommentDTO;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommentSubscriptionResolver implements GraphQLSubscriptionResolver {

    private final CommentPublisher commentPublisher;

    @Autowired
    public CommentSubscriptionResolver(CommentPublisher commentPublisher) {
        this.commentPublisher = commentPublisher;
    }

    public Publisher<CommentDTO> recentCommentByPost(UUID postId) {
        return this.commentPublisher.getRecentCommentByPostId(postId);
    }

}
