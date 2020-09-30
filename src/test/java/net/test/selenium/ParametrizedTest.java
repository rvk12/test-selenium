package net.test.selenium;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class ParametrizedTest {

    private final String login;
    private final String password;

    public ParametrizedTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Collection<String[]> data() {
        String[][] data = new String[][] { { "testlog1", "testpass1" }, { "testlog2", "testpass2" } };
        return Arrays.asList(data);
    }

    @Test
    public void pushTest() {
        System.setProperty("binary", "C:\\\\chrome\\\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://example.test");
        driver.findElement(By.id("loginField")).sendKeys(login);
        driver.findElement(By.id("loginField")).sendKeys(password);
        driver.findElement(By.id("loginButton")).click();
        driver.quit();
    }
}