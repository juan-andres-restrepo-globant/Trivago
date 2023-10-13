package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import util.screens.FavoritesScreen;
import util.screens.HomeScreen;
import util.tests.BaseMobileTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FavoritesSteps extends BaseMobileTest {
    FavoritesScreen favoritesScreen = new FavoritesScreen(getDriver());
    HomeScreen homeScreen = new HomeScreen(getDriver());

    @When("I tap the favorites tab")
    public void goToFavorites() {
        log.info("Go to favorites");
        favoritesScreen.goToFavorites();
    }

    @And("I tap on the start searching button")
    public void startSearching() {
        log.info("Start searching");
        favoritesScreen.startSearching();
    }

    @And("I search {string}")
    public void search(String city) {
        log.info("Searching");
        homeScreen.search(city);
        homeScreen.selectDates();
    }

    @And("I tap on the favorites logo at the items:")
    public void addFirstTwoFavorites(List<String> items) {
        log.info("Add to favorites");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        for (String item : items) {
            favoritesScreen.addFavorite(item);
        }
    }

    @And("I tap the green notification regarding to add the new favorite")
    public void goFavoriteNotification() {
        log.info("Going to the notification");
        favoritesScreen.goFavoriteNotification();
    }

    @Then("I can see the records that I marked as favorite")
    public void validateFavorites(List<String> items) {
        log.info("Validating favorites");
        for (String item : items) {
            Assert.assertTrue(favoritesScreen.checkFavorite(item));
        }
    }
}
