package com.gl.demo.graphql.service;

import com.gl.demo.graphql.dto.PostDTO;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<PostDTO> getPostsByAuthorId(UUID authorId);
    List<PostDTO> getrecentPosts(int count, int offset);
    UUID createPost(PostDTO post);
}
