package com.example.base.api.form;

import lombok.Setter;
import lombok.Getter;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddMovieForm {
    private Long movieId;

    @NotBlank(message = "movieName is not blank")
    @NotNull(message = "movieName is not null")
    private String movieName;

    private String description;
}
