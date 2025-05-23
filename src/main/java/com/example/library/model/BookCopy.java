package com.example.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // unique per copy

    private boolean isBorrowed = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "isbn") // FK to Book
    private Book book;

    @ManyToOne
    private Borrower borrower; // nullable
}


