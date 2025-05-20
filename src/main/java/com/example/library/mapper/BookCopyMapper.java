package com.example.library.mapper;

import com.example.library.dto.BookCopyResponseDto;
import com.example.library.dto.BorrowerDto;
import com.example.library.model.BookCopy;
import com.example.library.model.Borrower;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookCopyMapper {

    public BookCopyResponseDto convertToResponseDto(BookCopy bookCopy) {
        Borrower borrower = bookCopy.getBorrower();
        BorrowerDto borrowerDto = borrower != null ? BorrowerDto.builder()
                .id(borrower.getId())
                .name(borrower.getName())
                .email(borrower.getEmail())
                .build() : null;

        return BookCopyResponseDto.builder()
                .isbn(bookCopy.getBook().getIsbn())
                .title(bookCopy.getBook().getTitle())
                .author(bookCopy.getBook().getAuthor())
                .isBorrowed(bookCopy.isBorrowed())
                .borrower(borrowerDto)
                .build();
    }

    public List<BookCopyResponseDto> convertToResponseDtoList(List<BookCopy> bookCopies) {
        return bookCopies.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
}
