package dev.bestzige.springpractice.repositories;

import dev.bestzige.springpractice.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findFirstByOrderByPriceDesc();
    Page<Book> findByTitleContainingAndAuthorContainingAndPriceBetween(String title, String author, Double lowerPrice, Double upperPrice, Pageable pageable);
    @Query("SELECT b FROM Book b WHERE b.category.name LIKE %?1%")
    List<Book> getBooksByCategoryName(String categoryName);
}
