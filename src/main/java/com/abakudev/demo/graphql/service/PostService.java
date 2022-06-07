package com.abakudev.demo.graphql.service;

import com.abakudev.demo.graphql.dto.AuthorDTO;
import com.abakudev.demo.graphql.dto.PostDTO;

import java.util.List;
import java.util.UUID;

public interface PostService {

    List<PostDTO> getPostsByAuthorId(UUID authorId);
    List<PostDTO> getRecentPosts(int count, int offset);
    UUID createPost(PostDTO post);
    Integer getPostCountByAuthorId(UUID id);
    PostDTO getPostById(UUID postId);
    AuthorDTO getAuthorById(UUID authorId);
}
