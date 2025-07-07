package mb.projects.book_market.Book;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import mb.projects.book_market.User.User;
import mb.projects.book_market.User.UserRepository;

@Service
public class BookServices {

    private BookRepository bookRepo;
    private UserRepository userRepo;
    private ModelMapper mapper;

    public BookServices(BookRepository bookRepo,UserRepository userRepo, ModelMapper mapper) {
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    public List<Book> getAllBooks() {
        return this.bookRepo.findAll();
    }

    public Book getBookById(Long id) {
        Optional<Book> found = this.bookRepo.findById(id);
        if (found.isEmpty()) {
            return null;
        }
        Book result = found.get();

        return result;
    }

    public Book createBook(BookDTO data) throws Exception {
        Book newBook = mapper.map(data, Book.class);
        User owner = userRepo.findById(data.getUser_id()).orElseThrow(() -> new Exception("No User"));
        System.out.println("owner id: " + owner.getId());
        newBook.setUser(owner);
        bookRepo.save(newBook);
        return newBook;
    }


    public Book updateBook(Long id, UpdateBookDTO data) {
        Optional<Book> found = this.bookRepo.findById(id);
        if (found.isEmpty()) {
            return null;
        }
        Book result = found.get();
        mapper.map(data, result);
        bookRepo.save(result);
        return result;
    }

    public void deleteBook(Long id) {
        Book toDelete = getBookById(id);
        toDelete.setIsDeleted(Boolean.TRUE);
    }

}