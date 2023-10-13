package util.tests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import util.ConfigCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseMobileTest {
    public static AndroidDriver<MobileElement> driver;
    public Logger log= LogManager.getLogger(BaseMobileTest.class);

    @BeforeSuite
    public void environmentSetUp() {
        if (driver == null) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            ConfigCapabilities.deviceSetUp(capabilities);
            ConfigCapabilities.applicationSetUp(capabilities);
            try {
                driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            } catch (MalformedURLException exception) {
                exception.printStackTrace();
            }
        }
    }

    @AfterSuite
    public void mobileApplicationEnd() {
        driver.quit();
    }

    public AndroidDriver<MobileElement> getDriver() {
        environmentSetUp();
        return driver;
    }
}
