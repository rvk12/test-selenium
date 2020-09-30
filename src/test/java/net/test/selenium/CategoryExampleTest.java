package net.test.selenium;

import net.test.selenium.cats.BVT;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CategoryExampleTest {

    @Test
    @Category(BVT.class)
    public void test(){
        System.setProperty("binary", "C:\\\\chrome\\\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://google.com");
        driver.quit();
    }


}
