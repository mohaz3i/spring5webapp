package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
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

        Publisher publisher = new Publisher();
        publisher.setName("MDPI");
        publisher.setCity("Guercif");
        publisher.setZip("35100");
        publisher.setAddressLine("Lot rachidia 12");

        publisherRepository.save(publisher);
        System.out.println("Number of Publishers : " + publisherRepository.count());

        thesis.setPublisher(publisher);
        publisher.getBook().add(thesis);
        thesis2.setPublisher(publisher);
        publisher.getBook().add(thesis2);

        publisherRepository.save(publisher);
        System.out.println("This publisher has " + publisher.getBook().size() + " books published!");
    }
}
