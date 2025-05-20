//package com.example.library.repository;
//
//import com.example.library.model.Book;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // Prevent Spring from replacing your DB
//@ActiveProfiles("test") // Tells Spring to use application-test.properties
//class BookRepositoryTest {
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    @Test
//    @DisplayName("Should find books by ISBN from PostgreSQL DB")
//    void testFindByIsbn() {
////        Arrange
//        Book book1 = new Book();
//        book1.setTitle("Clean Code");
//        book1.setAuthor("Robert Martin");
//        book1.setIsbn("11111");
//
//        Book book2 = new Book();
//        book2.setTitle("Java Concurrency");
//        book2.setAuthor("Brian Goetz");
//        book2.setIsbn("11111");
//
//        Book book3 = new Book();
//        book3.setTitle("Spring in Action");
//        book3.setAuthor("Craig Walls");
//        book3.setIsbn("22222");
//
//        bookRepository.save(book1);
//        bookRepository.save(book2);
//        bookRepository.save(book3);
//
////      Act
//        List<Book> foundBooks = bookRepository.findByIsbn("11111");
//
////      Assert
//        assertThat(foundBooks).hasSize(2);
//        assertThat(foundBooks).extracting(Book::getTitle).containsExactlyInAnyOrder("Clean Code", "Java Concurrency");
//    }
//
//    @Test
//    @DisplayName("Test saving a Book")
//    void testSaveBook() {
////        Arrange
//        Book book = new Book();
//        book.setTitle("Clean Code");
//        book.setAuthor("Robert C. Martin");
//        book.setIsbn("9780132350884");
//
////        Act
//        Book savedBook = bookRepository.save(book);
//
////        Assert
//        assertThat(savedBook.getId()).isNotNull();
//        assertThat(savedBook.getTitle()).isEqualTo("Clean Code");
//    }
//
//    @Test
//    @DisplayName("Test finding all books")
//    void testFindAllBooks() {
////        Arrange
//        Book book1 = new Book();
//        book1.setTitle("Book One");
//        book1.setAuthor("Author A");
//        book1.setIsbn("ISBN-001");
//
//        Book book2 = new Book();
//        book2.setTitle("Book Two");
//        book2.setAuthor("Author B");
//        book2.setIsbn("ISBN-002");
//
////        Act
//        bookRepository.save(book1);
//        bookRepository.save(book2);
//
//        List<Book> books = bookRepository.findAll();
//
////        Assert
//        assertThat(books).hasSize(2);
//    }
//
//    @Test
//    @DisplayName("Test finding a book by ID")
//    void testFindById() {
////        Arrange
//        Book book = new Book();
//        book.setTitle("Domain-Driven Design");
//        book.setAuthor("Eric Evans");
//        book.setIsbn("9780321125217");
//
////        Act
//        Book savedBook = bookRepository.save(book);
//
//        Optional<Book> fetched = bookRepository.findById(savedBook.getId());
//
////        Assert
//        assertThat(fetched).isPresent();
//        assertThat(fetched.get().getTitle()).isEqualTo("Domain-Driven Design");
//    }
//}
