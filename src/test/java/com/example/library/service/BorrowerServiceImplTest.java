package com.example.library.service;

import com.example.library.dto.BorrowerDto;
import com.example.library.model.Borrower;
import com.example.library.repository.BorrowerRepository;
import com.example.library.serviceImpl.BorrowerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BorrowerServiceImplTest {

    @Mock
    private BorrowerRepository borrowerRepository;

    @InjectMocks
    private BorrowerServiceImpl borrowerService;

    private BorrowerDto borrowerDto;

    @BeforeEach
    void setUp() {
        borrowerDto = BorrowerDto.builder()
                .name("John Doe")
                .email("john@example.com")
                .build();
    }

    @Test
    void testRegisterBorrower_success() {
        Borrower savedBorrower = new Borrower();
        savedBorrower.setId(1L);
        savedBorrower.setName(borrowerDto.getName());
        savedBorrower.setEmail(borrowerDto.getEmail());

        when(borrowerRepository.save(any(Borrower.class))).thenReturn(savedBorrower);

        Borrower result = borrowerService.registerBorrower(borrowerDto);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetBorrowerById_found() {
        Borrower borrower = new Borrower();
        borrower.setId(1L);
        borrower.setName("John Doe");
        borrower.setEmail("john@example.com");

        when(borrowerRepository.findById(1L)).thenReturn(Optional.of(borrower));

        Borrower result = borrowerService.getBorrowerById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
    }

    @Test
    void testGetBorrowerById_notFound() {
        when(borrowerRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> borrowerService.getBorrowerById(1L));

        assertEquals("Borrower not found with ID: 1", exception.getMessage());
    }
}

