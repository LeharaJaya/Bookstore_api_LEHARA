package service;

import model.Book;
import exception.BookNotFoundException;

import java.util.*;

public class BookService {
    private static final Map<Integer, Book> books = new HashMap<>();
    private static int idCounter = 1;

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public Book getBook(int id) {
        Book book = books.get(id);
        if (book == null) throw new BookNotFoundException(id);
        return book;
    }

    public Book addBook(Book book) {
        book.setId(idCounter++);
        books.put(book.getId(), book);
        return book;
    }

    public Book updateBook(int id, Book updated) {
        if (!books.containsKey(id)) throw new BookNotFoundException(id);
        updated.setId(id);
        books.put(id, updated);
        return updated;
    }

    public void deleteBook(int id) {
        if (!books.containsKey(id)) throw new BookNotFoundException(id);
        books.remove(id);
    }
}
