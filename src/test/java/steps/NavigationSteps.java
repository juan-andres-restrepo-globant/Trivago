package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import util.screens.HomeScreen;
import util.tests.BaseMobileTest;

import java.util.List;

public class NavigationSteps extends BaseMobileTest {
    HomeScreen homeScreen = new HomeScreen(getDriver());

    @Given("The application is already running")
    public void applicationRunning() {
        log.info("The application is running");
    }

    @When("I check the navigation bar located at the bottom of the screen")
    public void ensureNavigationBar() {
        log.info("Ensure navigation bar");
        Assert.assertTrue(homeScreen.ensureNavigationBar());
    }

    @Then("I validate that the navigation bar contains:")
    public void validateNavigationBar(List<String> tabs) {
        log.info("Validate tabs");
        Assert.assertEquals(tabs, homeScreen.getTabs());
    }
}
