package com.example.library.service;

import com.example.library.dto.BookCopyResponseDto;
import com.example.library.model.BookCopy;

import java.util.List;

public interface BookCopyService {
    BookCopyResponseDto addBookCopy(String isbn);

    BookCopy borrowBookCopy(Long copyId, Long borrowerId);

    BookCopy returnBookCopy(Long copyId);

    List<BookCopyResponseDto> getAvailableCopies(String isbn);

}
