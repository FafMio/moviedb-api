package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Category;
import com.bezkoder.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);

    Boolean existsByTitle(String title);

    Category findCategoryById(Long id);
}
