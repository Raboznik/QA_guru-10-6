package ru.yandex.mkruchkov;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LabelsTest {

    @Test
    public void lambdaLabelsTest() {
        Allure.label("owner", "Maksim");
        Allure.feature("Issues");
        Allure.story("Create Issue for authorized user");
        Allure.label("displayName", "Create Issue for authorized user");
        Allure.label("severity", SeverityLevel.BLOCKER.value());
        Allure.link("GitHub", "https://github.com");
    }

    @Test
    @Owner("Maksim")
    @Feature("Issues")
    @Stories({
            @Story("Create Issue for authorized user"),
            @Story("Create Issue for repository page"),
    })
    @DisplayName("Create Issue for authorized user")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "GitHub", url = "https://github.com")
    public void annotatedLabelTest() {

    }
}
