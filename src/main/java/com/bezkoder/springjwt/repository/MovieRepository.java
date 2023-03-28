package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Movie;
import com.bezkoder.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);

    Optional<Movie> findByReleasedAtBetween(Date releasedAtTimeStart, Date releasedAtTimeEnd);

    Boolean existsByTitle(String title);

}
