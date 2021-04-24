package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepositories;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final BookRepositories bookRepositories;
    private final AuthorRepository authorRepositories;
    private final PublisherRepository publisherRepositories;

    public BootStrapData(BookRepositories bookRepositories, AuthorRepository authorRepositories, PublisherRepository publisherRepositories) {
        this.bookRepositories = bookRepositories;
        this.authorRepositories = authorRepositories;
        this.publisherRepositories = publisherRepositories;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepositories.save(eric);
        bookRepositories.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepositories.save(rod);
        bookRepositories.save(noEJB);

        System.out.println("Starting BootStrap");
        System.out.println("Number of books " + bookRepositories.count());

        Publisher p1 = new Publisher();
        p1.setName("lily");
        publisherRepositories.save(p1);
        System.out.println("Number of publisher " + publisherRepositories.count());
        //should print 1
    }
}
