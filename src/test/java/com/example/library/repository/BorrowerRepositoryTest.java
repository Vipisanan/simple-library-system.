//package com.example.library.repository;
//
//import com.example.library.model.Borrower;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // Prevent Spring from replacing your DB
//@ActiveProfiles("test") // Tells Spring to use application-test.properties
//class BorrowerRepositoryTest {
//
//    @Autowired
//    private BorrowerRepository borrowerRepository;
//
//    @Test
//    void testSaveBorrower() {
//        Borrower borrower = new Borrower();
//        borrower.setName("John Doe");
//
//        Borrower savedBorrower = borrowerRepository.save(borrower);
//
//        assertThat(savedBorrower.getId()).isNotNull();
//        assertThat(savedBorrower.getName()).isEqualTo("John Doe");
//    }
//
//    @Test
//    void testFindById() {
//        Borrower borrower = new Borrower();
//        borrower.setName("Jane Doe");
//        Borrower saved = borrowerRepository.save(borrower);
//
//        Optional<Borrower> found = borrowerRepository.findById(saved.getId());
//
//        assertThat(found).isPresent();
//        assertThat(found.get().getName()).isEqualTo("Jane Doe");
//    }
//}
