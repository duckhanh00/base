package com.example.base.service.impl;

import com.example.base.api.form.AddMovieForm;
import com.example.base.api.form.UpdateMovieForm;
import com.example.base.common.RestResponsePage;
import com.example.base.dto.MovieDTO;
import com.example.base.entity.Movie;
import com.example.base.ex.AppException;
import com.example.base.ex.ExceptionCode;
import com.example.base.service.MovieService;
import com.example.base.store.repository.MovieRepository;
import com.example.base.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Override
    public RestResponsePage<MovieDTO> getMovie(MovieDTO movieDTO, Integer page, Integer size) {
        log.info("getMovie - START");
        Pageable pageable = PageRequest.of((page-1), size);
        Page<Movie> moviePage = movieRepository.selectMovies(pageable);
        long numberOfElements = moviePage.getTotalElements();
        int totalPages = moviePage.getTotalPages();

        List<MovieDTO> movieDTOs = moviePage.toList().stream()
                .map(item -> MovieDTO.builder()
                        .movieId(item.getMovieId())
                        .movieName(item.getMovieName())
                        .description(item.getDescription())
                        .build()
                )
                .collect(Collectors.toList());
        log.info("getMovie - END");
        return new RestResponsePage<>(movieDTOs, page, size, numberOfElements, totalPages);
    }

    @Override
    public void addMovie(AddMovieForm addMovieForm, String clientMessageId) {
        Long movieId = addMovieForm.getMovieId();
        String movieName = addMovieForm.getMovieName();
        String description = addMovieForm.getDescription();
        log.info("addMovie - START");
        log.info("addMovie request: form - {}", new Object[]{Util.toJson(addMovieForm)});
        Optional<Movie> movieOptional = movieRepository.findByMovieId(movieId);
        if(movieOptional.isPresent()) {
            log.info("addMovie - Duplicate movie");
            throw new AppException(ExceptionCode.DUPLICATE_MOVIE, clientMessageId);
        }
        movieRepository.save(Movie.builder()
                .movieId(movieId)
                .movieName(movieName)
                .description(description)
                .createBy("khanh")
                .createAt(new Date())
                .build());
        log.info("addMovie - END");
    }

    @Override
    @Transactional
    public void updateMovie(Long movieId, UpdateMovieForm updateMovieForm, String clientMessageId) {
        String movieName = updateMovieForm.getMovieName();
        String description = updateMovieForm.getDescription();
        log.info("updateMovie - START");
        log.info("updateMovie request: movieId|Form - {}|{}", new Object[]{movieId, Util.toJson(updateMovieForm)});
        Optional<Movie> movieOptional = movieRepository.findByMovieId(movieId);
        if(movieOptional.isEmpty()) {
            log.info("addMovie - Not found movie");
            throw new AppException(ExceptionCode.NOT_FOUND_MOVIE, clientMessageId);
        }
        Movie movie = movieOptional.get();

        movieRepository.save(Movie.builder()
                .movieId(movie.getMovieId())
                .movieName(movieName != null ? movieName : movie.getMovieName())
                .description(description != null ? description : movie.getDescription())
                .createBy("Khanh")
                .createAt(new Date())
                .build());

        movie.setModifiedBy("khanh");
        movie.setModifiedAt(new Date());
        movieRepository.save(movie);

        log.info("updateMovie - END");
    }

    @Override
    @Transactional
    public void deleteMovie(Long movieId, String clientMessageId) {
        log.info("deleteMovie - START");
        log.info("deleteMovie request: movieId - {}", movieId);
        Optional<Movie> movieOptional = movieRepository.findByMovieId(movieId);
        if(movieOptional.isEmpty()) {
            log.info("addMovie - Not found movie");
            throw new AppException(ExceptionCode.NOT_FOUND_MOVIE, clientMessageId);
        }

        movieRepository.deleteById(movieId);
        log.info("deleteMovie - END");
    }
}
