package util;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomWait {
    // time in seconds for waits methods
    public final long MIN_WAIT_SECONDS = 1;
    public final long SHORT_WAIT_SECONDS = 5;
    public final long NORMAL_WAIT_SECONDS = 15;
    public final long MEDIUM_WAIT_SECONDS = 40;
    public final long LARGE_WAIT_SECONDS = 60;
    private static final int SECONDS_CONVERSION = 1000;

    public void waitElementVisibility(AndroidDriver<MobileElement> driver, WebElement element, long waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitElementInvisibility(AndroidDriver<MobileElement> driver, WebElement element, long waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitElementToBeClickable(AndroidDriver<MobileElement> driver, MobileElement element, long waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
