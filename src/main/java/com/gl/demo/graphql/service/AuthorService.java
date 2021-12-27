package com.gl.demo.graphql.service;

import com.gl.demo.graphql.dto.AuthorDto;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
    List<AuthorDto> getAuthors();
    AuthorDto getAuthorById(UUID authorId);
    UUID createAuthor(AuthorDto author);
}
