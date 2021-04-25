package tqs.lab5;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.platform.engine.Cucumber;

import java.time.LocalDateTime;
import java.time.MonthDay;
import java.util.*;

@Cucumber
public class BookSteps {
    Library library = new Library();
    List<Book> result = new ArrayList<>();

    @ParameterType("([0-9]{2})-([0-9]{2})-([0-9]{4})")
    public LocalDateTime mydate(String day, String month, String year){
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0,0);
    }

    @Given("(a|another) book with the title {string}, written by {string}, published in {mydate}")
    public void addNewBook(final String title, final String author, final LocalDateTime published) {
        Book book = new Book(title, author, published);
        library.addBook(book);
    }

    @When("the customer searches for books by {string}")
    public void the_customer_searches_for_books_by(String string) {
        result = library.findBooksByAuthor(string);
    }

    @When("the customer searches for books with the title {string}")
    public void the_customer_searches_for_the_book_with_the_title(String string) {
        result = library.findBookSimilarTitle(string);
    }

    @When("the customer searches for books published between {int} and {int}")
    public void searchBetweenYears(final int from, final int to) {
        result = library.findBooksBetween(from, to);
    }

    @When("the customer searches for books published between {mydate} and {mydate}")
    public void searchBetweenDates(final LocalDateTime from, final LocalDateTime to) {
        result = library.findBooksBetween(from, to);
    }

    @Then("{int} book(s) should have been found")
    public void resultSize(final int nbooks) {
        assert(result.size()==nbooks);
    }

    @Then("Book {int} should have the title {string}")
    public void titleCheck(final int position, final String title) {
        assert(result.get(position - 1).getTitle().equals(title));
    }

    @Then("it should have the title {string}")
    public void titleCheck(final String title) {
        assert(result.get(0).getTitle().equals(title));
    }

}
