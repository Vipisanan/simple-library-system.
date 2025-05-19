package com.example.library.service;

import com.example.library.dto.BorrowerDto;
import com.example.library.model.Borrower;
import com.example.library.repository.BorrowerRepository;
import com.example.library.serviceImpl.BorrowerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BorrowerServiceImplTest {

    @Mock
    private BorrowerRepository borrowerRepository;

    @InjectMocks
    private BorrowerServiceImpl borrowerService;

    @Test
    void testRegisterBorrower() {
        BorrowerDto dto = new BorrowerDto();
        dto.setName("John Doe");
        dto.setEmail("john.doe@example.com");

        Borrower savedBorrower = new Borrower();
        savedBorrower.setId(1L);
        savedBorrower.setName(dto.getName());
        savedBorrower.setEmail(dto.getEmail());

        when(borrowerRepository.save(any(Borrower.class))).thenReturn(savedBorrower);

        Borrower result = borrowerService.registerBorrower(dto);

        assertNotNull(result);
        assertEquals(dto.getName(), result.getName());
        assertEquals(dto.getEmail(), result.getEmail());
        verify(borrowerRepository).save(any(Borrower.class));
    }

    @Test
    void testGetBorrowerById_Found() {
        Long borrowerId = 1L;
        Borrower borrower = new Borrower();
        borrower.setId(borrowerId);
        borrower.setName("Jane Doe");
        borrower.setEmail("jane.doe@example.com");

        when(borrowerRepository.findById(borrowerId)).thenReturn(Optional.of(borrower));

        Borrower result = borrowerService.getBorrowerById(borrowerId);

        assertNotNull(result);
        assertEquals(borrowerId, result.getId());
        assertEquals("Jane Doe", result.getName());
    }

    @Test
    void testGetBorrowerById_NotFound() {
        Long borrowerId = 99L;

        when(borrowerRepository.findById(borrowerId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            borrowerService.getBorrowerById(borrowerId);
        });

        assertEquals("Borrower not found with ID: " + borrowerId, exception.getMessage());
    }
}
