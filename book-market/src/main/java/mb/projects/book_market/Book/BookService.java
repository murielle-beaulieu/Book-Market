package mb.projects.book_market.Book;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import mb.projects.book_market.EmailConfig.EmailService;
import mb.projects.book_market.User.User;
import mb.projects.book_market.User.UserRepository;

@Service
public class BookService {

    private BookRepository bookRepo;
    private UserRepository userRepo;
    private ModelMapper mapper;
    private EmailService emailService;

    public BookService(BookRepository bookRepo, UserRepository userRepo, ModelMapper mapper, EmailService emailService) {
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
        this.mapper = mapper;
        this.emailService = emailService;
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
        // newBook.setBookGenre(data.getBookGenre());
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
        
        // We mark the book copied as having been traded
        bookTraded.setOfferedInTrade((Boolean.TRUE));
        
        // We mark the new book that it's been received from a trade
        newBook.setReceivedFromTrade((Boolean.TRUE));
        
        // We save both the new instance and copied instance
        bookRepo.save(newBook);
        bookRepo.save(bookTraded);
    }

    public Book updateBook(Long id, UpdateBookDTO data) {
        Book book = getBookById(id);
        mapper.map(data, book);
        bookRepo.save(book);
        return book;
    }

    public void deleteBook(Long id) {
        Book book = getBookById(id);
        book.setIsDeleted(Boolean.TRUE);
        bookRepo.save(book);
    }

    public void markBookAsUnavailable(Long id) {
       Book book = getBookById(id);
       book.setIsAvailable(Boolean.FALSE);
       bookRepo.save(book);
    }

}