package com.example.library.serviceImpl;

import com.example.library.dto.BorrowerDto;
import com.example.library.model.Borrower;
import com.example.library.repository.BorrowerRepository;
import com.example.library.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BorrowerServiceImpl implements BorrowerService {

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Override
    public Borrower registerBorrower(BorrowerDto dto) {
        Borrower borrower = new Borrower();
        borrower.setName(dto.getName());
        borrower.setEmail(dto.getEmail());
        return borrowerRepository.save(borrower);
    }

    @Override
    public Borrower getBorrowerById(Long id) {
        Optional<Borrower> borrower = borrowerRepository.findById(id);
        return borrower.orElseThrow(() -> new RuntimeException("Borrower not found with ID: " + id));
    }
}

