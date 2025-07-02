package mb.projects.book_market.Book;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookServices bookServices;

    public BookController(BookServices bookServices) {
        this.bookServices = bookServices;
    }

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> found = this.bookServices.getAllBooks();
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book found = this.bookServices.getBookById(id);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Book> createBook(@RequestBody BookDTO data) {
        Book newBook = this.bookServices.createBook(data);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody UpdateBookDTO data) {
        Book updatedBook = this.bookServices.updateBook(id,data);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        this.bookServices.deleteBook(id);
        return new String("Successfully deleted book with ID: " + id);
    }

}
