package com.abakudev.demo.graphql.repository;

import com.abakudev.demo.graphql.model.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRespository extends JpaRepository<Comment, UUID> {

    List<Comment> findAllByAuthorId(UUID authorId, Pageable pageable);
    List<Comment> findAllByPostId(UUID postId, Pageable pageable);
}