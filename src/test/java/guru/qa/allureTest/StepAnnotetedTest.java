package guru.qa.allureTest;

import guru.qa.pages.GithubSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StepAnnotetedTest extends TestBase {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String NAME_ISSUE = "69 nice";

    private GithubSteps steps = new GithubSteps();

    @Test
    @Owner("Кочуров Д.Е")
    @Feature("Github")
    @Story("Поиск issues в репозитории")
    @DisplayName("Шаги с аннотацией @Step")

    public void testGithub() {
        steps.openPage()
             .searchForRepository(REPOSITORY)
             .goToRepository(REPOSITORY)
             .openIssueTab()
             .shouldSeeIssueWithNumber(NAME_ISSUE)
             .takeScreenshot();
    }
}
