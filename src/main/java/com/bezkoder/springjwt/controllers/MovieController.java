package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Movie;
import com.bezkoder.springjwt.repository.CategoryRepository;
import com.bezkoder.springjwt.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/movie")
public class MovieController {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping(value = "/")
    public ResponseEntity<?> getAllVerifiedMovies(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(value = "sortDirection", required = false, defaultValue = "ASC") String sortDirection
    ) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by("title").ascending() : Sort.by("title").descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Movie> movies = movieRepository.findAllByIsVerifiedTrue(pageable);
        List<Movie> listOfMovies = movies.getContent();
        HashMap<String, Object> response = new HashMap<String, Object>();

        HashMap<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("currentPage", movies.getNumber());
        pageData.put("totalPages", movies.getTotalPages());
        pageData.put("itemsPerPage", movies.getSize());
        pageData.put("itemsCount", movies.getTotalElements());
        pageData.put("isLastPage", movies.isLast());

        response.put("content", listOfMovies);
        response.put("pagination", pageData);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/unverified")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllUnverifiedMovies(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(value = "sortDirection", required = false, defaultValue = "ASC") String sortDirection
    ) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by("title").ascending() : Sort.by("title").descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Movie> movies = movieRepository.findAllByIsVerifiedFalse(pageable);
        List<Movie> listOfMovies = movies.getContent();
        HashMap<String, Object> response = new HashMap<String, Object>();

        HashMap<String, Object> pageData = new HashMap<String, Object>();
        pageData.put("currentPage", movies.getNumber());
        pageData.put("totalPages", movies.getTotalPages());
        pageData.put("itemsPerPage", movies.getSize());
        pageData.put("itemsCount", movies.getTotalElements());
        pageData.put("isLastPage", movies.isLast());

        response.put("content", listOfMovies);
        response.put("pagination", pageData);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneMovie(@PathVariable Long id) {
        Movie movie = movieRepository.findMovieById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createMovie(@RequestBody Movie movie) {

        Object response = movieRepository.save(movie);

        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {

        Movie currentMovie = movieRepository.findMovieById(id);

        if (currentMovie != null) {
            currentMovie.setTitle(movie.getTitle());
            currentMovie.setImageUrl(movie.getImageUrl());
            currentMovie.setTmdbId(movie.getTmdbId());
            currentMovie.setVerified(movie.getVerified());
            currentMovie.setOriginCountryShort(movie.getOriginCountryShort());
            currentMovie.setOriginCountry(movie.getOriginCountry());
            currentMovie.setCategories(movie.getCategories());
            currentMovie.setReleasedAt(movie.getReleasedAt());

            Object response = movieRepository.save(currentMovie);

            return ResponseEntity.accepted().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {

        Movie currentMovie = movieRepository.findMovieById(id);
        if (currentMovie != null) {
            movieRepository.delete(currentMovie);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/verify/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> verifyMovie(@PathVariable Long id) {
        Movie movie = movieRepository.findMovieById(id);
        if (movie != null) {
            movie.setVerified(true);
            movieRepository.save(movie);
            return ResponseEntity.accepted().body(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/unverify/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> unverifyMovie(@PathVariable Long id) {
        Movie movie = movieRepository.findMovieById(id);
        if (movie != null) {
            movie.setVerified(false);
            movieRepository.save(movie);
            return ResponseEntity.accepted().body(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
