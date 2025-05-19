package com.example.library.controller;

import com.example.library.dto.BorrowerDto;
import com.example.library.model.Borrower;
import com.example.library.service.BorrowerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {

    @Autowired
    private BorrowerService borrowerService;

    @PostMapping("/register")
    public ResponseEntity<Borrower> registerBorrower(@Valid @RequestBody BorrowerDto borrowerDto) {
        Borrower savedBorrower = borrowerService.registerBorrower(borrowerDto);
        return ResponseEntity.ok(savedBorrower);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrower> getBorrowerById(@PathVariable Long id) {
        Borrower borrower = borrowerService.getBorrowerById(id);
        return ResponseEntity.ok(borrower);
    }
}
