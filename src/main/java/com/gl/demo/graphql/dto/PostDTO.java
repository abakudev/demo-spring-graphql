package com.gl.demo.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private UUID id;
    private UUID authorId;
    private String title;
    private String description;
    private String category;
}
