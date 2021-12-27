package com.gl.demo.graphql.service.impl;

import com.gl.demo.graphql.dto.AuthorDto;
import com.gl.demo.graphql.model.Author;
import com.gl.demo.graphql.repository.AuthorRepository;
import com.gl.demo.graphql.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDto> getAuthors() {
        List<Author> authors = this.authorRepository.findAll();
        return authors.stream().map(
                author -> AuthorDto.builder()
                        .id(author.getId())
                        .name(author.getName())
                        .email(author.getEmail())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public AuthorDto getAuthorById(UUID authorId){
        Optional<Author> author = this.authorRepository.findById(authorId);
        return author.map(value -> AuthorDto.builder()
                .id(authorId)
                .name(value.getName())
                .email(value.getEmail())
                .build()).orElse(null);
    }

    @Override
    public UUID createAuthor(AuthorDto author) {
        Author newAuthor = Author.builder()
                .name(author.getName())
                .email(author.getEmail())
                .build();

        Author authorCreated = this.authorRepository.saveAndFlush(newAuthor);
        return authorCreated.getId();
    }

}
