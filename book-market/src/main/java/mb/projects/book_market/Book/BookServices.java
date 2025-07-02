package mb.projects.book_market.Book;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BookServices {

    private BookRepository repo;
    private ModelMapper mapper;

    public BookServices(BookRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
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

    public Book createBook(BookDTO data) {
        Book newBook = mapper.map(data, Book.class);
        repo.save(newBook);
        return newBook;
    }

    public Book updateBook(Long id, UpdateBookDTO data) {
        Optional<Book> found = this.repo.findById(id);
        if (found.isEmpty()) {
            return null;
        }
        Book result = found.get();
        mapper.map(data, result);
        repo.save(result);
        return result;
    }

    public void deleteBook(Long id) {
        Book toDelete = getBookById(id);
        toDelete.setIsDeleted(Boolean.TRUE);
    }

}
