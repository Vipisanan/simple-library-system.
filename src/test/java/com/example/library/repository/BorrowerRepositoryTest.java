package com.example.library.repository;

import com.example.library.model.Borrower;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // Prevent Spring from replacing your DB
@ActiveProfiles("test") // Tells Spring to use application-test.properties
public class BorrowerRepositoryTest {

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Test
    void testSaveAndFindById() {
        Borrower borrower = new Borrower();
        borrower.setName("John Doe");
        borrower.setEmail("john.doe@example.com");

        // Save borrower
        Borrower saved = borrowerRepository.save(borrower);

        // Retrieve borrower by id
        Optional<Borrower> found = borrowerRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("John Doe");
        assertThat(found.get().getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    void testDelete() {
        Borrower borrower = new Borrower();
        borrower.setName("Jane Smith");
        borrower.setEmail("jane.smith@example.com");

        Borrower saved = borrowerRepository.save(borrower);
        Long id = saved.getId();

        borrowerRepository.deleteById(id);

        Optional<Borrower> deleted = borrowerRepository.findById(id);
        assertThat(deleted).isNotPresent();
    }
}
