package com.gl.demo.graphql.service.impl;

import com.gl.demo.graphql.dto.PostDTO;
import com.gl.demo.graphql.exception.ResourceNotFound;
import com.gl.demo.graphql.model.Author;
import com.gl.demo.graphql.model.Post;
import com.gl.demo.graphql.repository.AuthorRepository;
import com.gl.demo.graphql.repository.PostRepository;
import com.gl.demo.graphql.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<PostDTO> getPostsByAuthorId(UUID authorId) {

        List<Post> posts = this.postRepository.findAllByAuthorId(authorId);

        return posts.stream().map(
                post -> PostDTO.builder()
                        .id(post.getId())
                        .authorId(authorId)
                        .title(post.getTitle())
                        .description(post.getDescription())
                        .category(post.getCategory())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getRecentPosts(int count, int offset) {
        Pageable pageable = PageRequest.of(offset, count);

        Page<Post> all = this.postRepository.findAll(pageable);

        return all.stream().map(
                post -> PostDTO.builder()
                        .id(post.getId())
                        .authorId(post.getAuthor().getId())
                        .title(post.getTitle())
                        .description(post.getDescription())
                        .category(post.getCategory())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public UUID createPost(PostDTO post) {
        Optional<Author> author = this.authorRepository.findById(post.getAuthorId());

        if(author.isEmpty()){
            throw new ResourceNotFound("Auhtor is not exist!");
        }

        Post newPost = Post.builder()
                .title(post.getTitle())
                .description(post.getDescription())
                .category(post.getCategory())
                .author(author.get())
                .build();

        Post postCreated = this.postRepository.saveAndFlush(newPost);
        return postCreated.getId();
    }

    @Override
    public Integer getPostCountByAuthorId(UUID id) {
        return this.postRepository.findAllByAuthorId(id).size();
    }

}
