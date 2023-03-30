package com.picom.apijwt.repository;

import com.picom.apijwt.models.Category;
import com.picom.apijwt.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByReleasedAtBetween(Date releasedAtTimeStart, Date releasedAtTimeEnd);

    Boolean existsByTitle(String title);

    Movie findMovieById(Long id);

    List<Movie> findMoviesByTitleLike(String title);

    Page<Movie> findAllByTitleLike(String title, Pageable pageable);
    Page<Movie> findAllByIsVerifiedTrue(Pageable pageable);
    Page<Movie> findAllByIsVerifiedFalse(Pageable pageable);
    Page<Movie> findAllByCategoriesContains(Category categories, Pageable pageable);
}
