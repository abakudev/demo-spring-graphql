package com.abakudev.demo.graphql.service;

import com.abakudev.demo.graphql.dto.CommentDTO;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    List<CommentDTO> getFirstFewCommentsByAuthorId(UUID authorId, Integer count);
    List<CommentDTO> getFirstFewCommentsByPostId(UUID postId, Integer count);
    List<CommentDTO> getComments(Integer count, Integer offset);
    UUID createComment(CommentDTO commentDTO);
}
