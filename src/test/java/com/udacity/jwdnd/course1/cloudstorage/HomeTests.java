package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomeTests {
    public static WebDriver webDriver;
    @LocalServerPort
    public int port;
    public String baseURL;
    WebDriverWait wait = new WebDriverWait(webDriver, 10);
    HomePage homePage = new HomePage(webDriver);

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

        String firstName = "Test";
        String lastName = "Test";
        String username = "Test";
        String password = "test";

        webDriver.get(baseURL + "/signup");

        SignupPage signupPage = new SignupPage(webDriver);
        signupPage.signup(firstName, lastName, username, password);

        webDriver.get(baseURL + "/login");

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(username, password);
    }

    @Test
    @Order(1)
    public void testNoteAdd() {
        String noteTitle = "Test";
        String noteDescription = "Test";

        homePage.enterNote(wait);
        homePage.addNote(noteTitle, noteDescription, wait);
        homePage.submitNote(wait);
        homePage.submitSuccess(wait);
        homePage.enterNote(wait);

        assertEquals(noteTitle, homePage.getNote(wait).getNotetitle());
        assertEquals(noteDescription, homePage.getNote(wait).getNotedescription());

        homePage.logout(wait);
    }

    @Test
    @Order(2)
    public void testNoteEdit() {
        String noteTitle = "Test1";
        String noteDescription = "Test1";

        homePage.enterNote(wait);
        homePage.noteEdit(noteTitle, noteDescription, wait);
        homePage.submitNote(wait);
        homePage.submitSuccess(wait);
        homePage.enterNote(wait);

        assertEquals(noteTitle, homePage.getNote(wait).getNotetitle());
        assertEquals(noteDescription, homePage.getNote(wait).getNotedescription());

        homePage.logout(wait);
    }

    @Test
    @Order(3)
    public void testNoteDelete() {
        homePage.enterNote(wait);
        homePage.noteDelete(wait);
        homePage.submitSuccess(wait);
        homePage.enterNote(wait);

        assertFalse(homePage.isExists(webDriver,"table-note"));

        homePage.logout(wait);
    }

    @Test
    @Order(4)
    public void testCredentialAdd() {
        String credentialUrl = "/test";
        String credentialUsername = "Test";
        String credentialPassword = "test";

        homePage.enterCredential(wait);
        homePage.addCredential(credentialUrl, credentialUsername, credentialPassword, wait);
        homePage.submitCredential(wait);
        homePage.submitSuccess(wait);
        homePage.enterCredential(wait);

        assertEquals(credentialUrl, homePage.getCredential(wait).getUrl());
        assertEquals(credentialUsername, homePage.getCredential(wait).getUsername());
//        assertEquals(credentialPassword, homePage.getCredential(wait).getPassword());

        homePage.logout(wait);
    }

    @Test
    @Order(5)
    public void testCredentialEdit() {
        String credentialUrl = "/test1";
        String credentialUsername = "Test1";
        String credentialPassword = "test1";

        homePage.enterCredential(wait);
        homePage.credentialEdit(credentialUrl, credentialUsername, credentialPassword, wait);
        homePage.submitCredential(wait);
        homePage.submitSuccess(wait);
        homePage.enterCredential(wait);

        assertEquals(credentialUrl, homePage.getCredential(wait).getUrl());
        assertEquals(credentialUsername, homePage.getCredential(wait).getUsername());
//        assertEquals(credentialPassword, homePage.getCredential(wait).getPassword());

        homePage.logout(wait);
    }

    @Test
    @Order(6)
    public void testCredentialDelete() {
        homePage.enterCredential(wait);
        homePage.credentialDelete(wait);
        homePage.submitSuccess(wait);
        homePage.enterCredential(wait);

        assertFalse(homePage.isExists(webDriver,"table-credential"));

        homePage.logout(wait);
    }
}
