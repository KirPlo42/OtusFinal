package components;

import org.openqa.selenium.WebDriver;
import pageObj.AbsPageObj;

public abstract class AbsBaseComponent extends AbsPageObj {

  public AbsBaseComponent(WebDriver driver) {
    super(driver);
  }

}
