package util.screens;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FavoritesScreen extends BaseScreen {
    public FavoritesScreen(AndroidDriver<MobileElement> driver) {
        super(driver);
    }

    public void goToFavorites() {
        MobileElement element = findByResourceId("com.trivago:id/action_favourites");
        click(element);
    }

    public void addFavorite(String item) {
        scrollResourceIdToText("com.trivago:id/fragmentAccommodationSearchResultsRecyclerView", item);
        MobileElement accommodation = driver.findElementByAndroidUIAutomator("new UiSelector().text(\"" + item + "\").fromParent(new UiSelector().resourceId(\"com.trivago:id/accommodationFavoriteButtonAnimationView\"))");
        click(accommodation);
    }

    public boolean checkFavorite(String item) {
        MobileElement element = scrollToText(item);
        return element.isDisplayed();
    }

    public void goFavoriteNotification() {
        MobileElement element = findByResourceId("com.trivago:id/snackbar_action");
        click(element);
    }

    public void startSearching() {
        MobileElement element = findByResourceId("com.trivago:id/favoritesEmptyWithAdvantagesWithAdvantagesStartSearchButton");
        click(element);
    }
}
