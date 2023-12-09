package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author moha = new Author("mohamed","zaifri");
        Book thesis = new Book("My thesis", "1234");

        moha.getBooks().add(thesis);
        thesis.getAuthors().add(moha);

        authorRepository.save(moha);
        bookRepository.save(thesis);

        Author hamza = new Author("hamza","khalloufi");
        Book thesis2 = new Book("hamza's thesis", "12345");

        hamza.getBooks().add(thesis2);
        thesis2.getAuthors().add(hamza);

        authorRepository.save(hamza);
        bookRepository.save(thesis2);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books created : "+ bookRepository.count());
        System.out.println("Number of Authors created : "+ authorRepository.count());

    }
}
