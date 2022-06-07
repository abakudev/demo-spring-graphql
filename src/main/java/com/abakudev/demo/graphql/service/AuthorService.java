package com.abakudev.demo.graphql.service;

import com.abakudev.demo.graphql.dto.AuthorDTO;

import java.util.List;
import java.util.UUID;

public interface AuthorService {

    List<AuthorDTO> getAuthors();
    AuthorDTO getAuthorById(UUID authorId);
    UUID createAuthor(AuthorDTO authorDto);
}
