package com.example.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    private String isbn;

    @NotBlank
    private String title;

    @NotBlank
    private String author;


}

