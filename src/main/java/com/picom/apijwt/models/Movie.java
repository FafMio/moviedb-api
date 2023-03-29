package com.picom.apijwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 255)
    private String title;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date releasedAt;

    @NotBlank
    @NotNull
    @Size(max = 255)
    private String imageUrl;

    @NotBlank
    @NotNull
    @Size(max = 50)
    private String originCountry;

    @Size(max = 10)
    private String tmdbId;

    @JsonIgnore

    private Boolean isVerified = false;

    @NotBlank
    @NotNull
    @Size(max = 5)
    private String originCountryShort;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_categories",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Movie() {

    }

    public Movie(Long id, String title, Date releasedAt, String imageUrl, String originCountry, String tmdbId, String originCountryShort, Set<Category> categories) {
        this.id = id;
        this.title = title;
        this.releasedAt = releasedAt;
        this.imageUrl = imageUrl;
        this.originCountry = originCountry;
        this.tmdbId = tmdbId;
        this.originCountryShort = originCountryShort;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleasedAt() {
        return releasedAt;
    }

    public void setReleasedAt(Date releasedAt) {
        this.releasedAt = releasedAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(String tmdbId) {
        this.tmdbId = tmdbId;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public String getOriginCountryShort() {
        return originCountryShort;
    }

    public void setOriginCountryShort(String originCountryShort) {
        this.originCountryShort = originCountryShort;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
