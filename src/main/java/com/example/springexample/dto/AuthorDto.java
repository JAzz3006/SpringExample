package com.example.springexample.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AuthorDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private Long rating;
//    @JsonIgnore
    private List<CommentDto> comments = new ArrayList<>();
}
