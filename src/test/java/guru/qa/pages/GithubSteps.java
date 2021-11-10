package guru.qa.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GithubSteps {

    @Step("Открываем главную страницу")
    public GithubSteps openPage() {
        open("https://github.com/");
        return this;
    }

    @Step("Ищем репозиторий {repository}")
    public GithubSteps searchForRepository(String repository) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repository);
        $(".header-search-input").submit();
        return this;
    }

    @Step("Переходим в репозиторий {repository}")
    public GithubSteps goToRepository(String repository) {
        $(By.linkText("eroshenkoam/allure-example")).click();
        return this;
    }

    @Step("Открываем таб Issues")
    public GithubSteps openIssueTab() {
        $(By.partialLinkText("Issues")).click();
        return this;
    }

    @Step("Проверяем что существует Issue с названием {nameIssue}")
    public GithubSteps shouldSeeIssueWithNumber(String nameIssue) {
        $(byText(nameIssue)).should(Condition.visible);
        return this;
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        final WebDriver driver = WebDriverRunner.getWebDriver();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
