package service;

import entities.Book;
import repository.BookRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {

    private final BookRepository repository = new BookRepository();

    public List<Book> getAvailableBooks() throws SQLException {
        return repository.findAll()
                .stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }
}
