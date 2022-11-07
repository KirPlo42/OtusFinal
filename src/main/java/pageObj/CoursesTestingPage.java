package pageObj;

import org.openqa.selenium.WebDriver;

public class CoursesTestingPage extends AbsPageObj{
    public CoursesTestingPage(WebDriver driver) {
        super(driver);
    }

    public void openTestingCoursePage(){
        driver.get(System.getProperty("base.url")+"/categories/testing/");
    }
}
