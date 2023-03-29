package com.picom.apijwt.repository;

import com.picom.apijwt.models.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);

    Optional<Movie> findByReleasedAtBetween(Date releasedAtTimeStart, Date releasedAtTimeEnd);

    Boolean existsByTitle(String title);

    Movie findMovieById(Long id);

    List<Movie> findMoviesByTitleLike(String title);

    Page<Movie> findAllByIsVerifiedTrue(Pageable pageable);
    Page<Movie> findAllByIsVerifiedFalse(Pageable pageable);
}
