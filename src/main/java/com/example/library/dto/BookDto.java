package com.example.library.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String isbn;
    private String title;
    private String author;
}
