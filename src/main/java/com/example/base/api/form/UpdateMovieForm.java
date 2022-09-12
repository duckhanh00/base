package com.example.base.api.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateMovieForm {
    @NotBlank(message = "movieName is not blank")
    @NotNull(message = "movieName is not null")
    private String movieName;

    private String description;
}
