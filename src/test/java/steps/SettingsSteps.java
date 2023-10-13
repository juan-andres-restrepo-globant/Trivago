package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import util.screens.HomeScreen;
import util.screens.SettingsScreen;
import util.tests.BaseMobileTest;

import java.util.Map;

public class SettingsSteps extends BaseMobileTest {
    SettingsScreen settingsScreen = new SettingsScreen(getDriver());
    HomeScreen homeScreen = new HomeScreen(getDriver());

    @When("I tap the settings tab")
    public void goSettings() {
        log.info("Go to settings");
        settingsScreen.goSettings();
    }

    @And("I tap the data privacy settings option")
    public void goDataPrivacy() {
        log.info("Go to data privacy");
        settingsScreen.goDataPrivacy();
    }

    @And("I disable each optional cookie")
    public void disableOptionalCookies() {
        log.info("Disabling optional cookies");
        settingsScreen.disableOptionalCookies();
    }

    @And("I tap the save privacy button")
    public void savePrivacy() {
        log.info("Save data privacy");
        settingsScreen.savePrivacy();
    }

    @Then("I can see the tile {string}")
    public void validateTitle(String title) {
        log.info("Checking title");
        Assert.assertEquals(title, settingsScreen.getTitle());
    }

    @And("I can validate the body text is {string}")
    public void validateCookiesText(String text) {
        log.info("Validating cookies text");
        Assert.assertEquals(text, settingsScreen.getBodyText());
    }

    @And("I can validate the mandatory cookies text is {string}")
    public void validateMandatoryCookiesText(String text) {
        log.info("Validating mandatory cookies text");
        Assert.assertEquals(text, settingsScreen.getMandatoryCookiesText());
    }

    @And("I can validate the optional cookies are:")
    public void ValidateOptionalCookiesText(Map<String, String> cookies) {
        log.info("Validating optional cookies name");
        Assert.assertEquals(cookies.get("functional"), settingsScreen.getFunctionalCookiesTitle());
        Assert.assertEquals(cookies.get("performance"), settingsScreen.getPerformanceCookiesTitle());
        Assert.assertEquals(cookies.get("marketing"), settingsScreen.getMarketingCookiesTitle());
    }

    @And("I can validate that each optional cookie has a switch")
    public void validateOptionalCookiesPresent() {
        log.info("Validating each optional cookie has a switch");
        Assert.assertTrue(settingsScreen.optionalCookiesSwitchDisplayed());
    }

    @Then("I validate that each optional cookie is unchecked")
    public void validateOptionalCookiesUnchecked() {
        log.info("Validating optional cookies unchecked");
        Assert.assertTrue(settingsScreen.areOptionalCookiesUnchecked());
    }

    @And("I validate that each optional cookie has the right text below")
    public void validateOptionalCookiesText(Map<String, String> cookies) {
        log.info("Validating text below optional cookies");
        Assert.assertEquals(cookies.get("functional"), settingsScreen.getFunctionalCookiesText());
        Assert.assertEquals(cookies.get("performance"), settingsScreen.getPerformanceCookiesText());
        Assert.assertEquals(cookies.get("marketing"), settingsScreen.getMarketingCookiesText());
    }

    @After
    public void afterSettings() {
        while (true) {
            try {
                homeScreen.goHome();
                if (homeScreen.ensureTrivagoLogo())
                    break;
            } catch (Exception ex) {
                settingsScreen.tapBack();
            }
        }

    }
}
