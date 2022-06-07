package com.abakudev.demo.graphql.resolver.comment;

import com.abakudev.demo.graphql.dto.CommentDTO;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@Component
public class CommentPublisher {

    private final Sinks.Many<CommentDTO> processor;

    public CommentPublisher() {
        this.processor = Sinks.many().replay().all();
    }

    public Publisher<CommentDTO> getRecentComment() {
        return this.processor.asFlux();
    }

    public void publish(CommentDTO commentDTO) {
        this.processor.emitNext(commentDTO, Sinks.EmitFailureHandler.FAIL_FAST);
    }

    public Publisher<CommentDTO> getRecentCommentByPostId(UUID postId) {
        return this.processor.asFlux()
                .filter(commentDTO -> postId.equals(commentDTO.getPostId()));
    }
}
