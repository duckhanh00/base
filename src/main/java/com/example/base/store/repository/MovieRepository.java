package com.example.base.store.repository;

import com.example.base.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT * FROM movie", nativeQuery = true)
    Page<Movie> selectMovies(Pageable pageable);

    @Query(value = "SELECT * FROM movie WHERE ID = :movieId", nativeQuery = true)
    Optional<Movie> selectMovie(Long movieId);

    Optional<Movie> findByMovieId(Long movieId);

    List<Movie> findByMovieName(String movieName);
}
