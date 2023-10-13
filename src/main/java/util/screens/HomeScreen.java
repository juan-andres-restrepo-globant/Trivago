package util.screens;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.remote.RemoteWebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class HomeScreen extends BaseScreen {
    public HomeScreen(AndroidDriver<MobileElement> driver) {
        super(driver);
    }

    public boolean ensureDates() {
        try {
            MobileElement dates = findByResourceId("com.trivago:id/activityDatesSelectionToolbar");
            return dates.isDisplayed();
        } catch (NotFoundException ex) {
            log.info("Element not displayed");
            return false;
        }
    }

    public boolean ensureTrivagoLogo() {
        MobileElement logo = findByResourceId("com.trivago:id/fragmentHomeLogoImageView");
        customWait.waitElementVisibility(driver, logo, customWait.NORMAL_WAIT_SECONDS);
        return logo.isDisplayed();
    }

    public boolean ensureNavigationBar() {
        MobileElement navigationBar = findByResourceId("com.trivago:id/bottomNavigation");
        customWait.waitElementVisibility(driver, navigationBar, customWait.NORMAL_WAIT_SECONDS);
        return navigationBar.isDisplayed();
    }

    public List<String> getTabs() {
        List<MobileElement> elements = driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.trivago:id/bottomNavigation\")").findElementsByClassName("android.widget.TextView");
        return elements.stream().map(RemoteWebElement::getText).collect(Collectors.toList());
    }

    public void goHome() {
        MobileElement element = findByResourceId("com.trivago:id/action_home");
        click(element);
    }

    public boolean pickDate(Date date) {
        SimpleDateFormat month = new SimpleDateFormat("MMMM 'de' yyyy", new Locale("es", "CO"));
        SimpleDateFormat day = new SimpleDateFormat("d");

        scrollResourceIdToText("com.trivago:id/activityDatesSelectionCalendarPickerView", month.format(date).toLowerCase());
        MobileElement element = driver.findElementByAndroidUIAutomator("new UiSelector().text(\"" + month.format(date) + "\").fromParent(new UiSelector().childSelector(new UiSelector().text(\"" + day.format(date) + "\")))");
        click(element);

        element = findByResourceId("com.trivago:id/activityDatesSelectionCalendarApplyTextView");
        return element.isEnabled();
    }

    public String rangeDates() {
        MobileElement element = findByResourceId("com.trivago:id/fragmentAccommodationSearchResultsExpandedDealformCalendarTextView");
        return element.getText();
    }

    public void search(String city) {
        MobileElement textButton = findByResourceId("com.trivago:id/fragmentHomeExpandedDealformDestinationTextView");
        click(textButton);
        MobileElement textBox = findByResourceId("com.trivago:id/activitySearchDestinationSearchEditText");
        sendKeys(textBox, city);
        MobileElement option = findFirstByClass("android.widget.Button");
        click(option);
    }

    public String searchedCity() {
        MobileElement element = findByResourceId("com.trivago:id/fragmentAccommodationSearchResultsExpandedDealformDestinationTextView");
        return element.getText();
    }

    public void selectDates() {
        MobileElement confirm = findByResourceId("com.trivago:id/activityDatesSelectionCalendarApplyTextView");
        click(confirm);
    }
}
