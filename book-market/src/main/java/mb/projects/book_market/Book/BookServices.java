package mb.projects.book_market.Book;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class BookServices {

    private BookRepository repo;

    public BookServices(BookRepository repo) {
        this.repo = repo;
    }

    public List<Book> getAllBooks() {
        return this.repo.findAll();
    }

    public Book getBookById(Long id) {
        Optional<Book> found = this.repo.findById(id);
        if (found.isEmpty()) {
            return null;
        }
        Book result = found.get();

        return result;
    }

}
