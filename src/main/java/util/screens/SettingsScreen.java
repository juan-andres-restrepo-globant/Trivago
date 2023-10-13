package util.screens;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.util.concurrent.TimeUnit;

public class SettingsScreen extends BaseScreen {
    public SettingsScreen(AndroidDriver<MobileElement> driver) {
        super(driver);
    }

    public boolean areOptionalCookiesUnchecked() {
        MobileElement functional = scrollClassToResourceId("android.widget.ScrollView", "com.trivago:id/activityDataManagerFunctionalityCookiesSwitch");
        if (functional.getAttribute("checked").equalsIgnoreCase("true"))
            return false;

        MobileElement performance = scrollClassToResourceId("android.widget.ScrollView", "com.trivago:id/activityDataManagerPerformanceCookiesSwitch");
        if (performance.getAttribute("checked").equalsIgnoreCase("true"))
            return false;


        MobileElement marketing = scrollClassToResourceId("android.widget.ScrollView", "com.trivago:id/activityDataManagerMarketingCookiesSwitch");
        return !marketing.getAttribute("checked").equalsIgnoreCase("true");
    }

    public String getBodyText() {
        MobileElement bodyText = findByResourceId("com.trivago:id/activtyDataManagerCookiesDescriptionTextView");
        return bodyText.getText();
    }

    public String getFunctionalCookiesText() {
        MobileElement element = scrollClassToResourceId("android.widget.ScrollView", "com.trivago:id/activityDataManagerFunctionalityCookiesTextView");
        return element.getText();
    }

    public String getFunctionalCookiesTitle() {
        MobileElement element = scrollClassToResourceId("android.widget.ScrollView", "com.trivago:id/activityDataManagerFunctionalityCookiesTitleTextView");
        return element.getText();
    }

    public String getMandatoryCookiesText() {
        MobileElement mandatory = findByResourceId("com.trivago:id/activityDataManagerRequiredCookiesTextView");
        return mandatory.getText();
    }

    public String getMarketingCookiesText() {
        MobileElement marketing = scrollClassToResourceId("android.widget.ScrollView", "com.trivago:id/activityDataManagerMarketingCookiesTextView");
        return marketing.getText();
    }

    public String getMarketingCookiesTitle() {
        MobileElement marketing = scrollClassToResourceId("android.widget.ScrollView", "com.trivago:id/activityDataManagerMarketingCookiesTitleTextView");
        return marketing.getText();
    }

    public String getPerformanceCookiesText() {
        MobileElement element = scrollClassToResourceId("android.widget.ScrollView", "com.trivago:id/activityDataManagerPerformanceCookiesTextView");
        return element.getText();
    }

    public String getPerformanceCookiesTitle() {
        MobileElement element = scrollClassToResourceId("android.widget.ScrollView", "com.trivago:id/activityDataManagerPerformanceCookiesTitleTextView");
        return element.getText();
    }

    public String getTitle() {
        MobileElement title = driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.trivago:id/activityDataManagerToolbar\").childSelector(new UiSelector().className(\"android.widget.TextView\"))");
        return title.getText();
    }

    public void goDataPrivacy() {
        MobileElement element = findByResourceId("com.trivago:id/fragmentSettingsManageMyDataTextView");
        click(element);
    }

    public void goSettings() {
        MobileElement element = findByResourceId("com.trivago:id/action_settings");
        click(element);
    }

    public void disableOptionalCookies() {
        MobileElement marketing = scrollClassToResourceId("android.widget.ScrollView", "com.trivago:id/activityDataManagerMarketingCookiesSwitch");
        MobileElement functional = findByResourceId("com.trivago:id/activityDataManagerFunctionalityCookiesSwitch");
        MobileElement performance = findByResourceId("com.trivago:id/activityDataManagerPerformanceCookiesSwitch");

        click(functional);
        click(performance);
        click(marketing);
    }

    public boolean optionalCookiesSwitchDisplayed() {
        MobileElement functional = scrollClassToResourceId("android.widget.ScrollView", "com.trivago:id/activityDataManagerFunctionalityCookiesSwitch");
        if (!functional.isDisplayed())
            return false;

        MobileElement performance = scrollClassToResourceId("android.widget.ScrollView", "com.trivago:id/activityDataManagerPerformanceCookiesSwitch");
        if (!performance.isDisplayed())
            return false;

        MobileElement marketing = scrollClassToResourceId("android.widget.ScrollView", "com.trivago:id/activityDataManagerMarketingCookiesSwitch");
        return marketing.isDisplayed();
    }

    public void savePrivacy() {
        MobileElement save = findByResourceId("com.trivago:id/activityDataManagerSaveButton");
        click(save);
    }
}
