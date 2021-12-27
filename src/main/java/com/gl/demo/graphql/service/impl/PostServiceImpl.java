package com.gl.demo.graphql.service.impl;

import com.gl.demo.graphql.dto.PostDTO;
import com.gl.demo.graphql.model.Post;
import com.gl.demo.graphql.repository.PostRepository;
import com.gl.demo.graphql.service.PostService;
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

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public List<PostDTO> getPostsByAuthorId(UUID authorId) {

        List<Post> posts = this.postRepository.findAllByAuthor_Id(authorId);

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
}
