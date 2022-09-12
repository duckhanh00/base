package com.example.base.controller;

import com.example.base.api.form.AddMovieForm;
import com.example.base.api.form.UpdateMovieForm;
import com.example.base.api.response.ApiResponse;
import com.example.base.api.response.SuccessResponse;
import com.example.base.dto.MovieDTO;
import com.example.base.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping()
    public ApiResponse getMovie(@RequestHeader(name = "clientMessageId") String clientMessageId,
                                @RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer size) {
        MovieDTO movieDTO = MovieDTO.builder().build();
        return new SuccessResponse(clientMessageId, movieService.getMovie(movieDTO, page, size));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse addMovie(@RequestHeader(name = "clientMessageId") String clientMessageId,
                                @RequestBody @Valid AddMovieForm addMovieForm) {
        movieService.addMovie(addMovieForm, clientMessageId);
        return new SuccessResponse(clientMessageId);
    }

    @PutMapping(path="/{movieId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse updateMovie(@RequestHeader(name = "clientMessageId") String clienMessageId,
                                   @Valid @PathVariable("movieId") Long movieId,
                                   @RequestBody @Valid UpdateMovieForm updateMovieForm) {
        movieService.updateMovie(movieId, updateMovieForm, clienMessageId);
        return new SuccessResponse(clienMessageId);
    }

    @DeleteMapping(path="/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse deleteMovie(@RequestHeader(name = "clientMessageId") String clienMessageId,
                                   @Valid @PathVariable("movieId") Long movieId) {
        movieService.deleteMovie(movieId, clienMessageId);
        return new SuccessResponse(clienMessageId);
    }
}
