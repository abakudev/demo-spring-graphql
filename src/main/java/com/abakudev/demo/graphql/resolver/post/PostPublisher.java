package com.abakudev.demo.graphql.resolver.post;

import com.abakudev.demo.graphql.dto.PostDTO;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@Component
public class PostPublisher {

    private final Sinks.Many<PostDTO> processor;

    public PostPublisher() {
        this.processor = Sinks.many().replay().all();
    }

    public Publisher<PostDTO> getRecentPost() {
        return this.processor.asFlux();
    }

    public void publish(PostDTO postDto) {
        this.processor.emitNext(postDto, Sinks.EmitFailureHandler.FAIL_FAST);
    }

    public Publisher<PostDTO> getRecentPostByAuthorId(UUID authorId) {
        return this.processor.asFlux().filter(postDTO -> authorId.equals(postDTO.getAuthorId()));
    }

}
