package mb.projects.book_market.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import mb.projects.book_market.Enums.BookGenre;
import mb.projects.book_market.User.User;
import mb.projects.book_market.User.UserRepository;

@Service
public class BookService {

    private BookRepository bookRepo;
    private UserRepository userRepo;
    private ModelMapper mapper;

    public BookService(BookRepository bookRepo, UserRepository userRepo, ModelMapper mapper) {
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    public List<Book> getAllBooks() {
        return this.bookRepo.findAll();
    }

    public List<Book> getBooksByAvailability(String filter) {

        List<Book> filtered = new ArrayList<>();

        if (filter.equalsIgnoreCase("available")) {
            filtered = getAllAvailableBooks();
        }

        if (filter.equalsIgnoreCase("unavailable")) {
            filtered = getAllUnavailableBooks();
        }

        return filtered;
    }

    public List<Book> getAllAvailableBooks() {
        List<Book> all = getAllBooks();
        return all.stream().filter(book -> !book.getIsDeleted() && book.getIsAvailable()).collect(Collectors.toList());
    }

    public List<Book> getAllUnavailableBooks() {
        List<Book> all = getAllBooks();
        return all.stream().filter(book -> !book.getIsDeleted() && !book.getIsAvailable()).collect(Collectors.toList());
    }

    public Book getBookById(Long id) {
        Optional<Book> found = this.bookRepo.findById(id);
        if (found.isEmpty()) {
            return null;
        }
        Book result = found.get();

        return result;
    }

    public List<Book> getBookByAuthor(String author) {
        List<Book> all = getAllBooks();
        return all.stream().filter(book -> book.getAuthor().equals(author)).collect(Collectors.toList());
    }

    public List<Book> getBookByGenre(String genre) {
        List<BookGenre> allBookGenres = Arrays.stream(BookGenre.values()).collect(Collectors.toList());
        BookGenre selectedGenre = allBookGenres.stream().filter(g -> g.toString().equals(genre)).findFirst().get();

        List<Book> all = getAllBooks();
        List<Book> byGenre = all.stream().filter(book -> book.getBookGenres().contains(selectedGenre))
                .collect(Collectors.toList());

        return byGenre;
    }

    public Book createBook(BookDTO data) throws Exception {
        Book newBook = mapper.map(data, Book.class);
        User owner = userRepo.findById(data.getUser_id())
                .orElseThrow(() -> new Exception("No User matching id: " + data.getUser_id()));
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
        book.setIsAvailable(Boolean.FALSE);
        bookRepo.save(book);
    }

    public void changeAvailability(Long id) {
        Book book = getBookById(id);
        book.setIsAvailable(!book.getIsAvailable());
        bookRepo.save(book);
    }

}