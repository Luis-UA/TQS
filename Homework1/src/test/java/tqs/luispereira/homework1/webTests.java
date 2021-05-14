package tqs.luispereira.homework1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class webTests {
    WebDriver browser;
    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.edge.driver", "C:\\WebDriver\\bin\\msedgedriver.exe");
        browser = new EdgeDriver();
    }

    @AfterEach
    void tearDown() {
        browser.close();
    }

    @Test
    public void checkParis() {
        browser.get("http://localhost:8080/");
        browser.manage().window().setSize(new Dimension(1397, 808));
        browser.findElement(By.id("tbMain")).click();
        browser.findElement(By.id("tbMain")).sendKeys("paris");
        browser.findElement(By.cssSelector(".form-group")).click();
        browser.findElement(By.cssSelector(".btn")).click();
        assertThat(browser.findElement(By.cssSelector("h1")).getText(), is("Paris"));
    }
}
