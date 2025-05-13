package service;

import model.Author;
import exception.AuthorNotFoundException;

import java.util.*;

public class AuthorService {
    private static final Map<Integer, Author> authors = new HashMap<>();
    private static int idCounter = 1;

    public List<Author> getAllAuthors() {
        return new ArrayList<>(authors.values());
    }

    public Author getAuthor(int id) {
        Author author = authors.get(id);
        if (author == null) throw new AuthorNotFoundException(id);
        return author;
    }

    public Author addAuthor(Author author) {
        author.setId(idCounter++);
        authors.put(author.getId(), author);
        return author;
    }

    public Author updateAuthor(int id, Author updated) {
        if (!authors.containsKey(id)) throw new AuthorNotFoundException(id);
        updated.setId(id);
        authors.put(id, updated);
        return updated;
    }

    public void deleteAuthor(int id) {
        if (!authors.containsKey(id)) throw new AuthorNotFoundException(id);
        authors.remove(id);
    }
}
