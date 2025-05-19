package com.example.library.service;

import com.example.library.dto.BorrowerDto;
import com.example.library.model.Borrower;

public interface BorrowerService {
    Borrower registerBorrower(BorrowerDto dto);
    Borrower getBorrowerById(Long id);
}
