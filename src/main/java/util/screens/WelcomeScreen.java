package util.screens;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NoSuchElementException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WelcomeScreen extends BaseScreen {

    public WelcomeScreen(AndroidDriver<MobileElement> driver) {
        super(driver);
    }

    public void avoidOffs() {
        MobileElement skip = findByResourceId("com.trivago:id/skipButton");
        click(skip);
    }

    public void confirmCookies() {
        MobileElement confirmCookies = findByResourceId("com.trivago:id/activityCookieConsentContentAcceptButton");
        confirmCookies.click();
    }

    public void confirmCountry() {
        MobileElement confirmButton = findByResourceId("com.trivago:id/activityPlatformSelectionConfirmButton");
        click(confirmButton);
        driver.manage().timeouts().implicitlyWait(customWait.NORMAL_WAIT_SECONDS, TimeUnit.SECONDS);
    }

    public Map<String, String> dataPrivacy() {
        MobileElement header = findByResourceId("com.trivago:id/activityCookieConsentContentHeaderTextView");
        customWait.waitElementVisibility(driver, header, customWait.LARGE_WAIT_SECONDS);

        MobileElement body = findByResourceId("com.trivago:id/activityCookieConsentContentDescriptionTextView");
        MobileElement choose = findByResourceId("com.trivago:id/activityCookieConsentContentChooseCookiesTextView");

        Map<String, String> privacy = new HashMap<>();
        privacy.put("header", header.getText());
        privacy.put("body", body.getText());
        privacy.put("choose", choose.getText());

        return privacy;
    }

    public void ensureWelcomeScreen() {
        driver.manage().timeouts().implicitlyWait(customWait.SHORT_WAIT_SECONDS, TimeUnit.SECONDS);
        MobileElement welcomeText = findByResourceId("com.trivago:id/activityPlatformSelectionWelcomeTextView");
        customWait.waitElementVisibility(driver, welcomeText, customWait.NORMAL_WAIT_SECONDS);
    }

    public void selectColombia() {
        String colombiaText = "Colombia (Español)";
        MobileElement colombia = scrollToText(colombiaText);
        click(colombia);
    }

    public boolean validateInvisibilityPrivacyPopUp() {
        try {
            MobileElement header = findByResourceId("com.trivago:id/activityCookieConsentContentHeaderTextView");
            customWait.waitElementInvisibility(driver, header, customWait.MEDIUM_WAIT_SECONDS);
            return header.isDisplayed();
        } catch (NoSuchElementException ex) {
            log.info("The privacy element already disappeared");
            return true;
        }
    }
}
