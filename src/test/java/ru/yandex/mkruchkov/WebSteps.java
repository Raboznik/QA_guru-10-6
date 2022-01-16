package ru.yandex.mkruchkov;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebSteps {
    @Step("Open main page")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Search repository {repository}")
    public void searchForRepository(String repository) {

        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repository);
        $(".header-search-input").submit();
    }

    @Step("Open repository {repository}")
    public void openRepositoryPage(String repository) {
        $(linkText(repository)).click();
    }

    @Step("Open Issues")
    public void openIssuesTab() {
        $(partialLinkText("Issues")).click();
    }

    @Step("Search Issue with number {number}")
    public void shouldSeeIssueWithNumber(int number) {
        $(withText("#" + number)).shouldHave(Condition.visible);

    }

    @Attachment(value = "Screenshot", type = "text/html", fileExtension = "html")
    public byte[] attachPageSource() {
        return WebDriverRunner.source().getBytes(StandardCharsets.UTF_8);

    }

}
