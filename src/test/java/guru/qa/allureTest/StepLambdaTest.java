package guru.qa.allureTest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class StepLambdaTest extends TestBase {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String NAME_ISSUE = "69 nice";

    @Test
    @Owner("Кочуров Д.Е")
    @Feature("Github")
    @Story("Поиск issues в репозитории")
    @DisplayName("Лямбда шаги через step")

    public void testGithub() {
        AllureLifecycle lifecycle = Allure.getLifecycle();

        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Переходим в репозиторий " + REPOSITORY, () -> {
            $(By.linkText("eroshenkoam/allure-example")).click();
        });
        step("Открываем таб Issues", () -> {
            $(By.partialLinkText("Issues")).click();
        });
        step("Проверяем что существует Issue с номером " + NAME_ISSUE, () -> {
            $(byText(NAME_ISSUE)).should(Condition.visible);
            lifecycle.addAttachment("Screenshot", "image/png", "png", takeScreenSchoot());
        });
    }

    private byte[] takeScreenSchoot() {
        final WebDriver driver = WebDriverRunner.getWebDriver();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
