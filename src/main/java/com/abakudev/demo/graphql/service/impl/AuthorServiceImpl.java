package com.abakudev.demo.graphql.service.impl;

import com.abakudev.demo.graphql.dto.AuthorDTO;
import com.abakudev.demo.graphql.exception.ResourceAlreadyExistsException;
import com.abakudev.demo.graphql.exception.ResourceNotFoundException;
import com.abakudev.demo.graphql.model.Author;
import com.abakudev.demo.graphql.repository.AuthorRepository;
import com.abakudev.demo.graphql.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AuthorDTO> getAuthors() {

        List<Author> authors = this.authorRepository.findAll();

        return authors.stream().map(
                author -> this.modelMapper.map(author, AuthorDTO.class)
        ).collect(Collectors.toList());
    }

    @Override
    public AuthorDTO getAuthorById(UUID authorId) {

        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author is not exist with id: " + authorId));

        return this.modelMapper.map(author, AuthorDTO.class);
    }

    @Override
    public UUID createAuthor(AuthorDTO author) {

        this.authorRepository.findByEmail(author.getEmail())
                .ifPresent(a -> {
                    throw new ResourceAlreadyExistsException("A author already exists with this email: " + a.getEmail());
                });

        Author newAuthor = this.modelMapper.map(author, Author.class);

        Author authorCreated = this.authorRepository.saveAndFlush(newAuthor);
        return authorCreated.getId();
    }

}
