package tqs.lab5;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private final List<Book> store = new ArrayList<>();

    public void addBook(final Book book) {
        store.add(book);
    }

    public List<Book> findBooksBetween(final LocalDateTime from, final LocalDateTime to) {
        return store.stream()
                .filter(book -> book.getPublished().isAfter(from) && book.getPublished().isBefore(to))
                .sorted(Comparator.comparing(Book::getPublished).reversed())
                .collect(Collectors.toList());
    }
    public List<Book> findBooksBetween(final int from, final int to) {
        LocalDateTime fromDate = LocalDateTime.of(from,1,1,0,0);
        LocalDateTime toDate = LocalDateTime.of(to,1,1,0,0);
        return store.stream()
                .filter(book -> book.getPublished().isAfter(fromDate) && book.getPublished().isBefore(toDate))
                .sorted(Comparator.comparing(Book::getPublished).reversed())
                .collect(Collectors.toList());
    }


    public List<Book> findBooksByAuthor(String author) {
        return store.stream().filter(book -> book.getAuthor().equals(author)).collect(Collectors.toList());
    }

    public List<Book> findBookSimilarTitle(String title) {
        return store.stream().filter(book -> book.getTitle().contains(title)).collect(Collectors.toList());
    }
}
