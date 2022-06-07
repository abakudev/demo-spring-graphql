package com.abakudev.demo.graphql.service.impl;

import com.abakudev.demo.graphql.dto.CommentDTO;
import com.abakudev.demo.graphql.exception.ResourceNotFoundException;
import com.abakudev.demo.graphql.model.Comment;
import com.abakudev.demo.graphql.repository.AuthorRepository;
import com.abakudev.demo.graphql.repository.CommentRespository;
import com.abakudev.demo.graphql.repository.PostRepository;
import com.abakudev.demo.graphql.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRespository commentRespository;
    private final AuthorRepository authorRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRespository commentRespository, AuthorRepository authorRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRespository = commentRespository;
        this.authorRepository = authorRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CommentDTO> getFirstFewCommentsByAuthorId(UUID authorId, Integer count) {

        List<Comment> commentsByAuthorId = this.commentRespository.findAllByAuthorId(authorId,
                PageRequest.of(0, count));

        return commentsByAuthorId.stream()
                .map( comment -> this.modelMapper.map(comment, CommentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getFirstFewCommentsByPostId(UUID postId, Integer count) {

        List<Comment> commentsByPostId = this.commentRespository.findAllByPostId(postId, PageRequest.of(0, count));

        return commentsByPostId.stream()
                .map( comment -> this.modelMapper.map(comment, CommentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> getComments(Integer count, Integer offset) {

        Page<Comment> comments = this.commentRespository.findAll(PageRequest.of(offset, count));

        return comments.stream()
                .map( comment -> this.modelMapper.map(comment, CommentDTO.class) )
                .collect(Collectors.toList());
    }

    @Override
    public UUID createComment(CommentDTO commentDTO) {

        if(this.authorRepository.findById(commentDTO.getAuthorId()).isEmpty()) {
            throw new ResourceNotFoundException("Author is not exist with id: " + commentDTO.getAuthorId());
        }
        if(this.postRepository.findById(commentDTO.getPostId()).isEmpty()) {
            throw new ResourceNotFoundException("Post is not exist with id: " + commentDTO.getPostId());
        }

        Comment comment =  this.modelMapper.map(commentDTO, Comment.class);

        Comment createdComment= this.commentRespository.saveAndFlush(comment);
        return createdComment.getId();
    }

}
