import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SeleniumJupiter.class)
class LAB3CLASSTestUsingJupiter {

    @Test
    public void site_header_is_on_home_page(ChromeDriver browser) {
        browser.get("https://www.saucelabs.com");
        WebElement href = browser.findElement(By.xpath("//a[@href='https://accounts.saucelabs.com/']"));
        assertTrue((href.isDisplayed()));
    }

    @Test //teste criado usando o Selenium IDE e exportado para JUnit (2a e 2b)
    public void test2(ChromeDriver browser) {
        browser.get("https://blazedemo.com/");
        browser.manage().window().setSize(new Dimension(1536, 824));
        browser.findElement(By.name("fromPort")).click();
        {
            WebElement dropdown = browser.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = 'San Diego']")).click();
        }
        browser.findElement(By.name("toPort")).click();
        {
            WebElement dropdown = browser.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = 'Cairo']")).click();
        }
        browser.findElement(By.cssSelector(".btn-primary")).click();
        assertThat(browser.findElement(By.cssSelector("h3")).getText(), is("Flights from San Diego to Cairo:"));
        assertThat(browser.findElement(By.cssSelector("th:nth-child(4)")).getText(), is("Departs: San Diego"));
        assertThat(browser.findElement(By.cssSelector("th:nth-child(5)")).getText(), is("Arrives: Cairo"));
        browser.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
        browser.findElement(By.cssSelector(".btn-primary")).click();
        {
            WebElement element = browser.findElement(By.cssSelector("body"));
            Actions builder = new Actions(browser);
            builder.moveToElement(element).clickAndHold().perform();
        }
        {
            WebElement element = browser.findElement(By.cssSelector("body"));
            Actions builder = new Actions(browser);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = browser.findElement(By.cssSelector("body"));
            Actions builder = new Actions(browser);
            builder.moveToElement(element).release().perform();
        }
        browser.findElement(By.cssSelector("body")).click();
        assertThat(browser.getTitle(), is("BlazeDemo Confirmation"));
    }
}