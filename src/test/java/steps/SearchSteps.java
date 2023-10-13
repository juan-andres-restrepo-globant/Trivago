package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import util.screens.HomeScreen;
import util.tests.BaseMobileTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SearchSteps extends BaseMobileTest {
    HomeScreen homeScreen = new HomeScreen(getDriver());

    @Given("The privacy data has already been accepted")
    public void privacyDataAlreadyAccepted() {
        log.info("Privacy data already accepted");
        Assert.assertTrue(homeScreen.ensureTrivagoLogo());
    }

    @When("I search {string} on the text box")
    public void searchTextBox(String city) {
        log.info("Searching " + city);
        Assert.assertFalse(homeScreen.ensureDates());
        homeScreen.search(city);
    }

    @And("I select the range of dates between {string}, {string}")
    public void searchDates(String start, String end) throws ParseException {
        Assert.assertTrue(homeScreen.ensureDates());

        log.info("Dates range");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date startDate = formatter.parse(start);
        Date endDate = formatter.parse(end);

        Assert.assertFalse(homeScreen.pickDate(startDate));
        Assert.assertTrue(homeScreen.pickDate(endDate));
    }

    @And("I tap the search button")
    public void search() {
        log.info("Confirm dates");
        homeScreen.selectDates();
    }

    @Then("I can see the text box that includes the word {string}")
    public void validateTextBox(String city) {
        log.info("Asserting city");
        Assert.assertEquals(city, homeScreen.searchedCity());
    }

    @And("I can see the range of dates concatenated like {string} - {string}")
    public void validateDates(String start, String end) throws ParseException {
        log.info("Asserting dates");
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat formatter = new SimpleDateFormat("d 'de' MMM", new Locale("es-CO"));

        String date = formatter.format(parser.parse(start)).toLowerCase() + " - " + formatter.format(parser.parse(end)).toLowerCase();
        Assert.assertEquals(date, homeScreen.rangeDates());
    }
}
