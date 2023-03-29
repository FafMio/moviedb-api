package com.bezkoder.springjwt.controllers;

//import com.bezkoder.springjwt.models.Category;
//import com.bezkoder.springjwt.models.Comment;
//import com.bezkoder.springjwt.repository.CategoryRepository;
//import com.bezkoder.springjwt.repository.CommentRepository;
//import com.bezkoder.springjwt.repository.MovieRepository;
//import com.bezkoder.springjwt.security.services.UserDetailsImpl;
//import com.bezkoder.springjwt.security.services.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/api/comment")
//public class CommentController {
//    @Autowired
//    MovieRepository movieRepository;
//
//    @Autowired
//    CommentRepository commentRepository;
//
////    @GetMapping(value = "/")
////    @ResponseStatus(HttpStatus.ACCEPTED)
////    public ResponseEntity<?> getAllCategories(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
////                                              @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
////                                              @RequestParam(value = "sortDirection", required = false, defaultValue = "ASC") String sortDirection
////    ) {
////        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by("title").ascending() : Sort.by("title").descending();
////        Pageable pageable = PageRequest.of(page, size, sort);
////
////        Page<Category> categories = categoryRepository.findAll(pageable);
////        List<Category> listOfCategories = categories.getContent();
////        HashMap<String, Object> response = new HashMap<String, Object>();
////
////        HashMap<String, Object> pageData = new HashMap<String, Object>();
////        pageData.put("currentPage", categories.getNumber());
////        pageData.put("totalPages", categories.getTotalPages());
////        pageData.put("itemsPerPage", categories.getSize());
////        pageData.put("itemsCount", categories.getTotalElements());
////        pageData.put("isLastPage", categories.isLast());
////
////        response.put("content", listOfCategories);
////        response.put("pagination", pageData);
////
////        return ResponseEntity.ok(response);
////    }
//
////    @GetMapping("/{id}")
////    public ResponseEntity<?> getOne(@PathVariable Long id) {
////        Category category = categoryRepository.findCategoryById(id);
////        if (category != null) {
////            return ResponseEntity.ok(category);
////        } else {
////            return ResponseEntity.notFound().build();
////        }
////    }
////
////    @PostMapping("/")
////    @ResponseStatus(HttpStatus.CREATED)
////    @PreAuthorize("hasRole('ADMIN')")
////    public ResponseEntity<?> create(@RequestBody Category category) {
////
////        Object response = categoryRepository.save(category);
////
////        return ResponseEntity.ok(response);
////    }
////
////    @PostMapping("/{id}")
////    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
////    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Category category) {
////
////        Category currentCategory = categoryRepository.findCategoryById(id);
////
////        if (currentCategory != null) {
////            currentCategory.setHexColor(category.getHexColor());
////            currentCategory.setTitle(category.getTitle());
////
////            Object response = categoryRepository.save(currentCategory);
////
////            return ResponseEntity.accepted().body(response);
////        } else {
////            return ResponseEntity.notFound().build();
////        }
////    }
//
//    @DeleteMapping("/{id}")
//    @PreAuthorize("isFullyAuthenticated()")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
//        Optional<Comment> comment = commentRepository.findById(id);
//
//        if(!comment.isPresent()) return ResponseEntity.notFound().build();
//
////        if(userDetails.getAuthorities())
//
//        if(!Objects.equals(comment.get().getAuthor().getId(), userDetails.getId()))  return ResponseEntity.status(403).build();
//
//        return ResponseEntity.ok(userDetails);
//
////        if(!Objects.equals(user_id, userDetails.getId())) {
////            return ResponseEntity.status(403).body(HttpStatus.FORBIDDEN.getReasonPhrase());
////        }
////
////        Comment comment = categoryRepository.findCategoryById(id);
////        if (currentCategory != null) {
////            categoryRepository.delete(currentCategory);
////            return ResponseEntity.noContent().build();
////        } else {
////            return ResponseEntity.notFound().build();
////        }
//    }
//
//}