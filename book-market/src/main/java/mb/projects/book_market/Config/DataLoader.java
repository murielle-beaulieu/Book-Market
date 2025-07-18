package mb.projects.book_market.Config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import mb.projects.book_market.Book.Book;
import mb.projects.book_market.Book.BookRepository;
import mb.projects.book_market.Enums.BookCondition;
import mb.projects.book_market.Enums.BookGenre;
import mb.projects.book_market.Enums.UserRole;
import mb.projects.book_market.User.User;
import mb.projects.book_market.User.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BookRepository bookRepository;

    public DataLoader(UserRepository userRepository, BookRepository bookRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findById((long)1) == null) {

            User user1 = new User("Raj", "Kumar", "rajkumar@bookmarket.com", passwordEncoder.encode("pass"), "raj444");
            user1.setIsDeleted(false);
            user1.setUserRole(UserRole.STANDARD);
            userRepository.save(user1);

            User user2 = new User("Ryan", "Thompson", "ryanthompson@bookmarket.com", passwordEncoder.encode("pass"),
                    "ryry_t");
            user2.setIsDeleted(false);
            user2.setUserRole(UserRole.STANDARD);
            userRepository.save(user2);

            User user3 = new User("Alex", "Poirier", "alexpoirier@bookmarket.com", passwordEncoder.encode("pass"),
                    "apple_poirier");
            user3.setIsDeleted(false);
            user3.setUserRole(UserRole.STANDARD);
            userRepository.save(user3);

            User user4 = new User("Cheah", "Davos", "cheahdavos@bookmarket.com", passwordEncoder.encode("pass"),
                    "cheah-12-d");
            user4.setIsDeleted(false);
            user4.setUserRole(UserRole.STANDARD);
            userRepository.save(user4);

            Book book1 = new Book(user1, "Jane Eyre",
                    "A novel of intense power and intrigue, Jane Eyre dazzled readers with its passionate depiction of a woman's search for equality and freedom.",
                    List.of(BookGenre.CLASSIC, BookGenre.ROMANCE), "Emily Bronte", "1846", "Penguin", "www.example",
                    BookCondition.GREAT);
            bookRepository.save(book1);

            Book book2 = new Book(user1, "The Diary Of A Young Girl",
                    "Anne Frank's extraordinary diary, written in the Amsterdam attic where she and her family hid from the Nazis for two years, has become a world classic and a timeless testament to the human spirit",
                    List.of(BookGenre.CLASSIC), "Anne Frank", "1947",
                    "Mass Market Paperback", "www.example", BookCondition.GOOD);
            bookRepository.save(book2);

            Book book3 = new Book(user2, "Stoner",
                    "John Williams’s luminous and deeply moving novel is a work of quiet perfection.",
                    List.of(BookGenre.CLASSIC, BookGenre.FICTION), "John Williams", "1965", "New York Review Books",
                    "www.example", BookCondition.GOOD);
            bookRepository.save(book3);

            Book book4 = new Book(user2, "East of Eden",
                    "Steinbeck created his most mesmerizing characters and explored his most enduring themes: the mystery of identity, the inexplicability of love, and the murderous consequences of love's absence.",
                    List.of(BookGenre.CLASSIC, BookGenre.HISTORICAL_FICTION), "John Steinbeck", "1952", "Penguin",
                    "www.example", BookCondition.GREAT);
            bookRepository.save(book4);

            Book book5 = new Book(user3, "Giovanni’s Room",
                    "Set in the contemporary Paris of American expatraites, liasons, and violence, a young man finds himself caught between desire and conventional morality.",
                    List.of(BookGenre.CLASSIC, BookGenre.ROMANCE), "James Baldwin", "1956", "Laurel", "www.example",
                    BookCondition.GOOD);
            bookRepository.save(book5);

            Book book6 = new Book(user3, "Sex and Rage",
                    "Sex and Rage delights in its sensuous, dreamlike narrative and spontaneous embrace of fate, work, and of certain meetings and chances.",
                    List.of(BookGenre.CONTEMPORARY, BookGenre.LITERARY_FICTION), "Eve Babitz", "1979", "Counterpoint",
                    "www.example", BookCondition.GREAT);
            bookRepository.save(book6);

            Book book7 = new Book(user4, "Caliban and the Witch: Women, the Body and Primitive Accumulation",
                    "Caliban and the Witch is a history of the body in the transition to capitalism. Moving from the peasant revolts of the late Middle Ages to the witch-hunts and the rise of mechanical philosophy",
                    List.of(BookGenre.PHILOSOPHY, BookGenre.HISTORY), "Silvia Federici", "2004", "Autonomedia",
                    "www.example", BookCondition.LIKE_NEW);
            bookRepository.save(book7);

            Book book8 = new Book(user4, "The Curious Incident of the Dog in the Night-Time",
                    "This improbable story of Christopher’s quest to investigate the suspicious death of a neighborhood dog",
                    List.of(BookGenre.CONTEMPORARY, BookGenre.YOUNG_ADULT), "Mark Haddon", "2003", "Vintage",
                    "www.example", BookCondition.GOOD);
            bookRepository.save(book8);

        } else {
           System.out.println("There seems to be users in your database, no need to seed :)");
        }
    }
}
