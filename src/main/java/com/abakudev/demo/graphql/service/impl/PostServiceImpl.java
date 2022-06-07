package com.abakudev.demo.graphql.service.impl;

import com.abakudev.demo.graphql.dto.AuthorDTO;
import com.abakudev.demo.graphql.dto.PostDTO;
import com.abakudev.demo.graphql.exception.ResourceNotFoundException;
import com.abakudev.demo.graphql.model.Author;
import com.abakudev.demo.graphql.model.Post;
import com.abakudev.demo.graphql.repository.AuthorRepository;
import com.abakudev.demo.graphql.repository.PostRepository;
import com.abakudev.demo.graphql.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PostDTO> getPostsByAuthorId(UUID authorId) {

        List<Post> posts = this.postRepository.findAllByAuthorId(authorId);

        return posts.stream().map(
                post -> this.modelMapper.map(post, PostDTO.class)
        ).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getRecentPosts(int count, int offset) {
        Pageable pageable = PageRequest.of(offset, count);

        Page<Post> all = this.postRepository.findAll(pageable);

        return all.stream().map(
                post -> this.modelMapper.map(post, PostDTO.class)
        ).collect(Collectors.toList());
    }

    @Override
    public UUID createPost(PostDTO post) {

        if(this.authorRepository.findById(post.getAuthorId()).isEmpty()){
            throw new ResourceNotFoundException("Author is not exist with id: " + post.getAuthorId());
        }

        Post newPost = this.modelMapper.map(post, Post.class);

        Post postCreated = this.postRepository.saveAndFlush(newPost);
        return postCreated.getId();
    }

    @Override
    public Integer getPostCountByAuthorId(UUID id) {
        return this.postRepository.findAllByAuthorId(id).size();
    }

    @Override
    public PostDTO getPostById(UUID postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post is not exist with id: " + postId));

        return this.modelMapper.map(post, PostDTO.class);
    }

    @Override
    public AuthorDTO getAuthorById(UUID authorId) {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author is not exist with id: " + authorId));

        return this.modelMapper.map(author, AuthorDTO.class);
    }

}
