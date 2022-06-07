package com.abakudev.demo.graphql.exception;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;

public class ResourceAlreadyExistsException extends RuntimeException implements GraphQLError {

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return Collections.emptyList();
    }

    @Override
    public ErrorClassification getErrorType() {
        return ErrorType.DataFetchingException;
    }

}
