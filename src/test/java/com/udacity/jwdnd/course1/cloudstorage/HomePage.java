package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private JavascriptExecutor javascriptExecutor;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        javascriptExecutor = (JavascriptExecutor) webDriver;
    }

    public void fileUpload(WebDriverWait wait, WebDriver driver) {
        String path = "/Users/iorxan/Downloads/1616377996369.jpeg";
        WebElement navFilesTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-files-tab")));
        javascriptExecutor.executeScript("arguments[0].click();", navFilesTab);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("fileUpload"))).sendKeys(path);
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit-button")));
        javascriptExecutor.executeScript("arguments[0].click();", submitButton);
        WebElement success = wait.until(ExpectedConditions.elementToBeClickable(By.id("success")));
        javascriptExecutor.executeScript("arguments[0].click();", success);
    }

    public void fileView(WebDriverWait wait) {
        WebElement navFilesTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-files-tab")));
        javascriptExecutor.executeScript("arguments[0].click();", navFilesTab);
        WebElement fileView = wait.until(ExpectedConditions.elementToBeClickable(By.id("fileView")));
        javascriptExecutor.executeScript("arguments[0].click();", fileView);
    }

    public void fileDelete(WebDriverWait wait) {
        WebElement navFilesTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-files-tab")));
        javascriptExecutor.executeScript("arguments[0].click();", navFilesTab);
        WebElement fileDelete = wait.until(ExpectedConditions.elementToBeClickable(By.id("fileDelete")));
        javascriptExecutor.executeScript("arguments[0].click();", fileDelete);
        WebElement success = wait.until(ExpectedConditions.elementToBeClickable(By.id("success")));
        javascriptExecutor.executeScript("arguments[0].click();", success);
    }


    public void enterNote(WebDriverWait wait) {
        WebElement navNotesTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-notes-tab")));
        javascriptExecutor.executeScript("arguments[0].click();", navNotesTab);
    }

    public void submitNote(WebDriverWait wait) {
        WebElement noteSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.id("note-submit")));
        javascriptExecutor.executeScript("arguments[0].click();", noteSubmit);
    }

    public void addNote(String noteTitle, String noteDescription, WebDriverWait wait) {
        WebElement addNote = wait.until(ExpectedConditions.elementToBeClickable(By.id("addNote")));
        javascriptExecutor.executeScript("arguments[0].click();", addNote);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("note-title"))).sendKeys(noteTitle);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("note-description"))).sendKeys(noteDescription);
    }

    public void noteEdit(String noteTitle, String noteDescription, WebDriverWait wait) {
        WebElement noteEdit = wait.until(ExpectedConditions.elementToBeClickable(By.id("noteEdit")));
        javascriptExecutor.executeScript("arguments[0].click();", noteEdit);
        WebElement notetitle = wait.until(ExpectedConditions.elementToBeClickable(By.id("note-title")));
        notetitle.clear();
        notetitle.sendKeys(noteTitle);
        WebElement notedescription = wait.until(ExpectedConditions.elementToBeClickable(By.id("note-description")));
        notedescription.clear();
        notedescription.sendKeys(noteDescription);
    }

    public void noteDelete(WebDriverWait wait) {
        WebElement noteDelete = wait.until(ExpectedConditions.elementToBeClickable(By.id("noteDelete")));
        javascriptExecutor.executeScript("arguments[0].click();", noteDelete);
    }


    public void enterCredential(WebDriverWait wait) {
        WebElement navCredentialsTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-credentials-tab")));
        javascriptExecutor.executeScript("arguments[0].click();", navCredentialsTab);
    }

    public void submitCredential(WebDriverWait wait) {
        WebElement credentialSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-submit")));
        javascriptExecutor.executeScript("arguments[0].click();", credentialSubmit);
    }

    public void addCredential(String credentialUrl, String credentialUsername, String credentialPassword, WebDriverWait wait) {
        WebElement addCredential = wait.until(ExpectedConditions.elementToBeClickable(By.id("addCredential")));
        javascriptExecutor.executeScript("arguments[0].click();", addCredential);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-url"))).sendKeys(credentialUrl);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-username"))).sendKeys(credentialUsername);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-password"))).sendKeys(credentialPassword);
    }

    public void credentialEdit(String credentialUrl, String credentialUsername, String credentialPassword, WebDriverWait wait) {
        WebElement credentialEdit = wait.until(ExpectedConditions.elementToBeClickable(By.id("credentialEdit")));
        javascriptExecutor.executeScript("arguments[0].click();", credentialEdit);
        WebElement credentialurl = wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-url")));
        credentialurl.clear();
        credentialurl.sendKeys(credentialUrl);
        WebElement credentialusername = wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-username")));
        credentialusername.clear();
        credentialusername.sendKeys(credentialUsername);
        WebElement credentialpassword = wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-password")));
        credentialpassword.clear();
        credentialpassword.sendKeys(credentialPassword);
    }

    public void credentialDelete(WebDriverWait wait) {
        WebElement credentialDelete = wait.until(ExpectedConditions.elementToBeClickable(By.id("credentialDelete")));
        javascriptExecutor.executeScript("arguments[0].click();", credentialDelete);
    }

    public void submitSuccess(WebDriverWait wait) {
        WebElement success = wait.until(ExpectedConditions.elementToBeClickable(By.id("success")));
        javascriptExecutor.executeScript("arguments[0].click();", success);
    }

    public void logout(WebDriverWait wait) {
        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout")));
        javascriptExecutor.executeScript("arguments[0].click();", logout);
    }

    public boolean isExists(WebDriver driver, String element) {
        try {
            driver.findElement(By.id(element));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public Note getNote(WebDriverWait wait) {
        String noteTitle = wait.until(ExpectedConditions.elementToBeClickable(By.id("noteTitle"))).getText();
        String noteDescription = wait.until(ExpectedConditions.elementToBeClickable(By.id("noteDescription"))).getText();
        return new Note(null, noteTitle, noteDescription, null);
    }

    public Credential getCredential(WebDriverWait wait) {
        String credentialUrl = wait.until(ExpectedConditions.elementToBeClickable(By.id("credentialUrl"))).getText();
        String credentialUsername = wait.until(ExpectedConditions.elementToBeClickable(By.id("credentialUsername"))).getText();
        String credentialPassword = wait.until(ExpectedConditions.elementToBeClickable(By.id("credentialPassword"))).getText();
        return new Credential(null, credentialUrl, credentialUsername, null, credentialPassword, null);
    }
}
