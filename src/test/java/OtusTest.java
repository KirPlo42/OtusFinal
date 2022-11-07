import components.LessonsCardsComponent;
import components.MainMenuComponent;
import data.CoursesData;
import data.events.EventsData;
import data.events.EventsTypeData;
import data.MainMenuItemsData;
import driver.DriverFactory;
import exceptions.DriverNotSupportedException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pageObj.*;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static driver.DriverFactory.setDriverName;

@Slf4j
public class OtusTest {
    private WebDriver driver;

    @BeforeAll
    public static void init() throws DriverNotSupportedException {
        new DriverFactory().init();
    }

    @BeforeEach
    public void initDriver() throws DriverNotSupportedException {
        List<String> options = new ArrayList<>();
        options.add("--window-size=1920,1080");
        driver = new DriverFactory().create(setDriverName(), options);
        log.info("Test started in {} browser", setDriverName());
    }

    @AfterEach
    public void close() {
        if (this.driver != null) {
            this.driver.close();
            this.driver.quit();
        }
    }

    @Test
    public void checkAmountOfCoursesInTestSection() {
        new DriverFactory().setImplicitlyWait(driver);
        new MainPage(driver).openMainPage();
        new MainMenuComponent(driver).clickMainMenuSection(MainMenuItemsData.Courses, CoursesData.Testing.getName());
        new LessonsCardsComponent(driver).checkAmountOfCourses(14);
    }

    @Test
    public void checkCardOfCourse() {
        new DriverFactory().setImplicitlyWait(driver);
        new CoursesTestingPage(driver).openTestingCoursePage();
        new LessonsCardsComponent(driver).clickSomeCard(1);
        new SomeCoursePage(driver).checkInfoAboutCourse();
    }

    @Test
    public void checkDatesForFutureEvents() throws InterruptedException, ParseException {
        new DriverFactory().setImplicitlyWait(driver);
        new MainPage(driver).openMainPage();
        new MainMenuComponent(driver).clickMainMenuSection(MainMenuItemsData.Events, EventsData.CalendarOfEvents.getName());
        EventsPage eventsPage = new EventsPage(driver);
        eventsPage.downToThePage();
        eventsPage.checkDates();
    }

    @Test
    public void checkByEventsType() throws InterruptedException {
        new DriverFactory().setImplicitlyWait(driver);
        EventsPage eventsPage = new EventsPage(driver);
        eventsPage.openEventsPage();
        eventsPage.openEventsTypeSelector();
        eventsPage.selectEventType(EventsTypeData.OpenWebinars);
        eventsPage.downToThePage();
        eventsPage.checkEventType(EventsTypeData.OpenWebinars);
    }

}
