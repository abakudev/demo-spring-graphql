package com.abakudev.demo.graphql.resolver.post;

import com.abakudev.demo.graphql.dto.PostDTO;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PostSubscriptionResolver implements GraphQLSubscriptionResolver {

    private final PostPublisher postPublisher;

    @Autowired
    public PostSubscriptionResolver(PostPublisher postPublisher) {
        this.postPublisher = postPublisher;
    }

    public Publisher<PostDTO> recentPost() {
        return this.postPublisher.getRecentPost();
    }

    public Publisher<PostDTO> recentPostByAuthor(UUID authorId) {
        return this.postPublisher.getRecentPostByAuthorId(authorId);
    }

}
