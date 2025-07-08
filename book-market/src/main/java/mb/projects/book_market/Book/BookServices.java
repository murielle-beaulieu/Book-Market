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

    public BookServices(BookRepository bookRepo, UserRepository userRepo, ModelMapper mapper) {
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
        newBook.setUser(owner);
        bookRepo.save(newBook);
        return newBook;
    }

    public void tradeBook(Book book, Long id) {
        // We find the book we want to copy
        Book bookTraded = bookRepo.findById(book.getId()).get();
        
        // We create a new book instance and map to a new instance
        Book newBook = new Book();
        newBook = mapper.map(bookTraded, Book.class);

        // We must reset the newBook's Id - otherwise it copies the existing book's id
        newBook.setId(null);

        System.out.println("the new book: " + newBook.getTitle());

        User userReceivingBook = userRepo.findById(id).get();
        newBook.setUser(userReceivingBook);

        System.out.println("the new book owner: " + newBook.getUser().getId());

        bookRepo.save(newBook);
        // We mark the book copied as having been traded
        bookTraded.setIsTraded(Boolean.TRUE);

        // We save both the new instance and copied instance
        bookRepo.save(bookTraded);

        System.out.println("trade user offering")
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