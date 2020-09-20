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

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design","123123");
        Publisher petr = new Publisher("Petr","Pushkina","Moscow","Russia","zip");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        publisherRepository.save(petr);

        System.out.println("Publisher Count: "+publisherRepository.count());

        ddd.setPublisher(petr);
        petr.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(petr);


        Author rod = new Author("Rob", "Johnson");
        Book noEJD = new Book("J2EE Development without EJB", "534254325");
        rod.getBooks().add(noEJD);
        noEJD.getAuthors().add(rod);

        noEJD.setPublisher(petr);
        petr.getBooks().add(noEJD);

        authorRepository.save(rod);
        bookRepository.save(noEJD);
        publisherRepository.save(petr);


        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: "+bookRepository.count());
        System.out.println("Publisher Number of Books: "+petr.getBooks().size());


    }
}
