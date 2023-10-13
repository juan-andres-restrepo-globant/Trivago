package util.screens;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import util.CustomWait;

import java.util.List;

import static java.lang.String.format;

public abstract class BaseScreen {
    protected CustomWait customWait = new CustomWait();
    protected final AndroidDriver<MobileElement> driver;
    public Logger log = LogManager.getLogger(BaseScreen.class);

    public BaseScreen(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    protected MobileElement findByResourceId(String id) {
        return driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"" + id + "\")");
    }

    protected MobileElement findByText(String text) {
        MobileElement element = driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + text + "\")");
        customWait.waitElementVisibility(driver, element, customWait.NORMAL_WAIT_SECONDS);
        return element;
    }

    protected MobileElement findFirstByClass(String className) {
        List<MobileElement> elements = driver.findElementsByAndroidUIAutomator("new UiSelector().className(\"" + className + "\")");
        MobileElement element = elements.get(0);
        customWait.waitElementVisibility(driver, element, customWait.NORMAL_WAIT_SECONDS);
        return element;
    }

    public void tapBack() {
        driver.navigate()
                .back();
    }

    protected void scrollDown(int swipes) {
        String locator = "new UiScrollable(new UiSelector().resourceIdMatches(\".*ontainer.*\")).flingToEnd(1)";
        scroll(locator, swipes);
    }

    protected void scrollUp(int swipes) {
        String locator = "new UiScrollable(new UiSelector().resourceIdMatches(\".*ontainer.*\")).flingToBeginning(1)";
        scroll(locator, swipes);
    }

    protected void scroll(String locator, int swipes) {
        int swipesAmount = 0;
        while (swipesAmount < swipes) {
            try {
                driver.findElementByAndroidUIAutomator(locator);
                swipesAmount++;
            } catch (Exception e) {
                swipesAmount++;
            }
        }
    }

    @SuppressWarnings({"rawtypes", "unused"})
    protected void swipeVertical(float percentage) {
        Dimension windowSize = driver.manage().window().getSize();
        TouchAction ta = new TouchAction(driver);
        ta.press(PointOption.point(207, 582)).moveTo(PointOption.point(8,
                -360)).release().perform();
    }

    protected MobileElement scrollClassToResourceId(String className, String id) {
        String automator = "new UiScrollable(new UiSelector().className(\"" + className + "\")).scrollIntoView(new UiSelector().resourceId(\"%s\"))";
        MobileElement element = driver.findElementByAndroidUIAutomator(format(automator, id));

        if (element.getLocation().getY() > driver.manage().window().getSize().getHeight() * 0.8) {
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()");
        }
        return element;
    }

    protected MobileElement scrollToResourceId(String id) {
        String automator = "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().resourceId(\"%s\"))";
        MobileElement element = driver.findElementByAndroidUIAutomator(format(automator, id));

        if (element.getLocation().getY() > driver.manage().window().getSize().getHeight() * 0.8) {
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()");
        }
        return element;
    }

    protected MobileElement scrollToText(String text) {
        String automator = "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().textContains(\"%s\"))";
        MobileElement element = driver.findElementByAndroidUIAutomator(format(automator, text));

        if (element.getLocation().getY() > driver.manage().window().getSize().getHeight() * 0.8) {
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()");
        }
        return element;
    }

    protected MobileElement scrollResourceIdToText(String id, String text) {
        String automator = "new UiScrollable(new UiSelector().resourceId(\"" + id + "\")).scrollIntoView(new UiSelector().textContains(\"%s\"))";
        MobileElement element = driver.findElementByAndroidUIAutomator(format(automator, text));

        if (element.getLocation().getY() > driver.manage().window().getSize().getHeight() * 0.8) {
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()");
        }
        return element;
    }

    protected void click(MobileElement element, int timeout) {
        customWait.waitElementVisibility(driver, element, timeout);
        element.click();
    }

    protected void click(MobileElement element) {
        customWait.waitElementVisibility(driver, element, customWait.LARGE_WAIT_SECONDS);
        element.click();
    }

    protected void sendKeys(MobileElement element, String sequence) {
        customWait.waitElementVisibility(driver, element, customWait.NORMAL_WAIT_SECONDS);
        element.sendKeys(sequence);
    }

    protected boolean isElementAvailable(MobileElement element) {
        customWait.waitElementVisibility(driver, element, customWait.NORMAL_WAIT_SECONDS);
        return true;
    }
}
