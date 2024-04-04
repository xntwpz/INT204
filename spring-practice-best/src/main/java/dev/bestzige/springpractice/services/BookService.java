package dev.bestzige.springpractice.services;

import dev.bestzige.springpractice.dtos.BookDTO;
import dev.bestzige.springpractice.dtos.CreateBookDto;
import dev.bestzige.springpractice.entities.Book;
import dev.bestzige.springpractice.entities.Category;
import dev.bestzige.springpractice.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public BookService(BookRepository bookRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    public Book getBook(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Page<Book> getBooks(String title, String author, Double lowerPrice, Double upperPrice, String[] sortBy, String[] direction, int pageNo, int pageSize) {
        if (lowerPrice <= 0 && upperPrice <= 0) {
            Book firstByOrderByPriceDesc = bookRepository.findFirstByOrderByPriceDesc();
            upperPrice = firstByOrderByPriceDesc != null ? firstByOrderByPriceDesc.getPrice() : 0;
        }

        if (lowerPrice > upperPrice) {
            double tmp = lowerPrice;
            lowerPrice = upperPrice;
            upperPrice = tmp;
        }

        Pageable pageable;
        if (sortBy != null && sortBy.length > 0) {
            List<Sort.Order> sortOrderList = new ArrayList<>();
            for (int i = 0; i < sortBy.length; i++) {
                if (Objects.nonNull(direction[i])) {
                    sortOrderList.add(new Sort.Order(Sort.Direction.valueOf(direction[i].toUpperCase()), sortBy[i]));
                }
            }
            Sort sort = Sort.by(sortOrderList);
            pageable = PageRequest.of(pageNo, pageSize, sort);
        } else {
            pageable = PageRequest.of(pageNo, pageSize);
        }

        return bookRepository.findByTitleContainingAndAuthorContainingAndPriceBetween(title, author, lowerPrice, upperPrice, pageable);
    }

    @Transactional
    public BookDTO createBook(CreateBookDto data) {
        Category category = categoryService.getCategory(data.getCategoryId());
        if (category == null) throw categoryService.notFound(data.getCategoryId());
        Book book = modelMapper.map(data, Book.class);
        book.setId(null);
        return modelMapper.map(bookRepository.saveAndFlush(book), BookDTO.class);
    }

    @Transactional
    public BookDTO updateBook(Integer id, CreateBookDto data) {
        Book book = getBook(id);
        if (book == null) throw notFound(id);
        Category category = categoryService.getCategory(data.getCategoryId());
        if (category == null) throw categoryService.notFound(data.getCategoryId());
        if (!book.getCategory().getId().equals(data.getCategoryId())) throw categoryService.conflict(data.getCategoryId(), book.getCategory().getId());
        modelMapper.map(data, book);
        return modelMapper.map(bookRepository.saveAndFlush(book), BookDTO.class);
    }

    @Transactional
    public BookDTO deleteBook(Integer id) {
        Book book = getBook(id);
        if (book == null) throw notFound(id);
        bookRepository.delete(book);
        return modelMapper.map(book, BookDTO.class);
    }

    /* Error Response */
    public ResponseStatusException notFound(Integer id) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Book id " + id + " DOES NOT EXIST !!!"
        );
    }

    public ResponseStatusException conflict(Integer id, Integer bookId) {
        return new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Conflict Book id !!! (" + id + " vs " + bookId + ")"
        );
    }
}
