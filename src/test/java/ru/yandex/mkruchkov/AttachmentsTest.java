package ru.yandex.mkruchkov;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class AttachmentsTest {

    static String repository = "eroshenkoam/allure-example";
    static int number = 68;
    WebSteps steps = new WebSteps();

    @Test
    public void lambdaAttachmentTest() {

        step("Open main page", () -> {
            open("https://github.com");
        });

        step("Search repository " + repository, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(repository);
            $(".header-search-input").submit();
        });

        step("Open repository " + repository, () -> {
            $(linkText(repository)).click();
        });

        step("Open Issues", () -> {
            Allure.addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
            $(partialLinkText("Issues")).click();
        });

        step("Search Issue with number " + number, () -> {
            $(withText("#" + number)).shouldHave(Condition.visible);
        });
    }


    @Test
    public void annotatedStepsTest() {
        steps.openMainPage();
        steps.searchForRepository(repository);
        steps.openRepositoryPage(repository);
        steps.openIssuesTab();
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(150);
    }

    @AfterEach
    public void saveSources() {
        steps.attachPageSource();
    }
}
