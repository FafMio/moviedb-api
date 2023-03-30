package com.picom.apijwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 20)
    private String title;

    @NotBlank
    @NotNull
    @Size(max = 8)
    private String bgHexColor;

    @NotBlank
    @NotNull
    @Size(max = 8)
    private String textHexColor;


    public Category() {

    }

    public Category(Long id, String title, String bgHexColor, String textHexColor) {
        this.id = id;
        this.title = title;
        this.bgHexColor = bgHexColor;
        this.textHexColor = textHexColor;
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

    public String getBgHexColor() {
        return bgHexColor;
    }

    public void setBgHexColor(String bgHexColor) {
        this.bgHexColor = bgHexColor;
    }

    public String getTextHexColor() {
        return textHexColor;
    }

    public void setTextHexColor(String textHexColor) {
        this.textHexColor = textHexColor;
    }
}
