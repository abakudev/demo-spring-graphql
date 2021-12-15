package com.gl.demo.graphql.service;

import com.gl.demo.graphql.dto.AuthorDTO;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
    List<AuthorDTO> getAuthors();
    AuthorDTO getAuthorById(UUID authorId);
}
