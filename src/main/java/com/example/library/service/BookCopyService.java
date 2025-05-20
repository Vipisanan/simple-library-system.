package com.example.library.service;

import com.example.library.model.BookCopy;

import java.util.List;

public interface BookCopyService {
    BookCopy addBookCopy(String isbn);

    BookCopy borrowBookCopy(Long copyId, Long borrowerId);

    BookCopy returnBookCopy(Long copyId);

    List<BookCopy> getAvailableCopies(String isbn);

}
