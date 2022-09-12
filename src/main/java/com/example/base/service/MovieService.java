package com.example.base.service;

import com.example.base.api.form.AddMovieForm;
import com.example.base.api.form.UpdateMovieForm;
import com.example.base.common.RestResponsePage;
import com.example.base.dto.MovieDTO;

public interface MovieService {
    RestResponsePage getMovie(MovieDTO movieDTO, Integer page, Integer size);
    void addMovie(AddMovieForm addMovieForm, String clientMessageId);
    void updateMovie(Long movieId, UpdateMovieForm updateMovieForm, String clientMessageId);
    void deleteMovie(Long movieId, String clientMessageId);
}
