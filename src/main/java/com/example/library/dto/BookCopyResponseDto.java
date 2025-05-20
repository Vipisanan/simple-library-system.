package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCopyResponseDto {
    private String isbn;
    private String title;
    private String author;
    private boolean isBorrowed;
    private BorrowerDto borrower;
}
