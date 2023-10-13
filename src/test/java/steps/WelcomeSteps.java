package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import util.screens.HomeScreen;
import util.screens.WelcomeScreen;
import util.tests.BaseMobileTest;

import java.util.Map;

public class WelcomeSteps extends BaseMobileTest {
    private final WelcomeScreen welcomeScreen = new WelcomeScreen(getDriver());
    private final HomeScreen homeScreen = new HomeScreen(getDriver());

    @Given("The application is in a fresh install")
    public void applicationRunning() throws Throwable {
        log.info("Validating on boarding page");
        welcomeScreen.ensureWelcomeScreen();
    }

    @When("I want to select Colombia")
    public void selectColombia() throws Throwable {
        log.info("Searching Colombia");
        welcomeScreen.selectColombia();
    }

    @And("I want to tap the confirm button")
    public void confirmCountry(Map<String, String> texts) throws Throwable {
        log.info("Confirm country");
        welcomeScreen.confirmCountry();
        Map<String, String> dataPrivacy = welcomeScreen.dataPrivacy();

        Assert.assertEquals(texts.get("header"), dataPrivacy.get("header"));
        Assert.assertEquals(texts.get("body"), dataPrivacy.get("body"));
        Assert.assertEquals(texts.get("choose"), dataPrivacy.get("choose"));
    }

    @And("I want to tap the agree all button")
    public void agreeAllButton() throws Throwable {
        log.info("Confirm cookies");
        welcomeScreen.confirmCookies();
        log.info("Avoid offs");
        welcomeScreen.avoidOffs();
    }

    @Then("I want to validate that the privacy data screen is not visible anymore")
    public void validatePrivacyData() throws Throwable {
        log.info("Validating");
        Assert.assertTrue(welcomeScreen.validateInvisibilityPrivacyPopUp());
        Assert.assertTrue(homeScreen.ensureTrivagoLogo());
    }
}
