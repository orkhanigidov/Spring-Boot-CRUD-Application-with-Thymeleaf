package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTests {
    public static WebDriver webDriver;
    @LocalServerPort
    public int port;
    public String baseURL;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        webDriver.quit();
        webDriver = null;
    }

    @BeforeEach
    public void beforeEach() {
        baseURL = "http://localhost:" + port;
    }

    @Test
    public void testUserSignupLogin() {
        String firstName = "Test";
        String lastName = "Test";
        String username = "Test";
        String password = "test";

        webDriver.get(baseURL + "/signup");
        assertEquals("Sign Up", webDriver.getTitle());

        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        SignupPage signupPage = new SignupPage(webDriver);
        signupPage.signup(firstName, lastName, username, password);

        webDriver.get(baseURL + "/login");
        assertEquals("Login", webDriver.getTitle());

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(username, password);

        assertEquals("Home", webDriver.getTitle());

        HomePage homePage = new HomePage(webDriver);
        homePage.logout(wait);

        assertEquals("Login", webDriver.getTitle());

        webDriver.get(baseURL + "/home");
        assertEquals("Login", webDriver.getTitle());
    }
}
