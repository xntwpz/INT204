package dev.bestzige.springpractice.controllers;

import dev.bestzige.springpractice.dtos.*;
import dev.bestzige.springpractice.entities.Book;
import dev.bestzige.springpractice.services.BookService;
import dev.bestzige.springpractice.utils.ListMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final ModelMapper modelMapper;
    private final ListMapper listMapper;

    @Autowired
    public BookController(BookService bookService, ModelMapper modelMapper, ListMapper listMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
        this.listMapper = listMapper;
    }

    @GetMapping("")
    public ResponseEntity<PageDTO<BooksDTO>> getBooks(@RequestParam(defaultValue = "") String title,
                               @RequestParam(defaultValue = "") String author,
                               @RequestParam(defaultValue = "0") Double lowerPrice,
                               @RequestParam(defaultValue = "0") Double upperPrice,
                               @RequestParam(defaultValue = "") String[] sortBy,
                               @RequestParam(defaultValue = "ASC") String[] direction,
                               @RequestParam(defaultValue = "0") @Min(value = 0, message = "Page number must be greater than 0") int pageNo,
                               @RequestParam(defaultValue = "10") @Min(value = 10, message = "Page size must be greater than 10") @Max(value = 20, message = "Page size must be less than 20") int pageSize
    ) {
        Page<Book> bookPage = bookService.getBooks(title, author, lowerPrice, upperPrice, sortBy, direction, pageNo, pageSize);
        PageDTO<BooksDTO> pageDTO = listMapper.toPageDTO(bookPage, BooksDTO.class, modelMapper);
        return ResponseEntity.ok(pageDTO);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer bookId) {
        Book book = bookService.getBook(bookId);
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        return ResponseEntity.ok(bookDTO);
    }

    @PostMapping("")
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody CreateBookDto book) {
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Integer bookId, @Valid @RequestBody CreateBookDto book) {
        return ResponseEntity.ok(bookService.updateBook(bookId, book));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable Integer bookId) {
        return ResponseEntity.ok(bookService.deleteBook(bookId));
    }
}
