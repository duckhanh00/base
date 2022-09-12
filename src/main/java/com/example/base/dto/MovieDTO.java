package com.example.base.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MovieDTO {
    private Long movieId;
    private String movieName;
    private String description;
}
