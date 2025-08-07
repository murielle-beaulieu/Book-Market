package mb.projects.book_market.Book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mb.projects.book_market.Enums.Role;
import mb.projects.book_market.Role.AllowedRoles;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookServices;

    public BookController(BookService bookServices) {
        this.bookServices = bookServices;
    }

    @AllowedRoles({ Role.ADMIN })
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> all = bookServices.getAllBooks();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @AllowedRoles({ Role.ADMIN })
    @GetMapping("/availability")
    public ResponseEntity<List<Book>> getBooksByAvailability(@RequestParam String status) throws Exception {
        List<Book> booksByAvailability = new ArrayList<>();
        booksByAvailability = bookServices.getBooksByAvailability(status);
        return new ResponseEntity<>(booksByAvailability, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Book>> getAvailableBooks() {
        List<Book> availableBooks = bookServices.getAllAvailableBooks();
        return new ResponseEntity<>(availableBooks, HttpStatus.OK);
    }

    @GetMapping("/author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam String author) {
    List<Book> booksByAuthor = bookServices.getBookByAuthor(author);
    return new ResponseEntity<>(booksByAuthor, HttpStatus.OK);
    }

    @GetMapping("/genre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam String genre) {
    List<Book> booksByGenre = bookServices.getBookByGenre(genre);
    return new ResponseEntity<>(booksByGenre, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book found = bookServices.getBookById(id);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Book> createBook(@RequestBody BookDTO data) throws Exception {
        Book newBook = this.bookServices.createBook(data);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody UpdateBookDTO data) {
        Book updatedBook = bookServices.updateBook(id, data);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @PatchMapping("/changeAvailability/{id}")
    public void changeAvailability(@PathVariable Long id) {
        bookServices.changeAvailability(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookServices.deleteBook(id);
        return new String("Successfully deleted book with ID: " + id);
    }

}
