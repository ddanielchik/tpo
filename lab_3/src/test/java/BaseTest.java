import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    protected Utils utils;
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        utils = new Utils();
        utils.setupDriver();
        driver = utils.getDriver();
    }

    @AfterEach
    public void tearDown() {
        if (utils != null) {
            utils.closeDriver();
        }
    }
}