import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MagPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MagazineTest {

    private Utils utils;
    private WebDriver driver;

    private MagPage magPage;

    @BeforeEach
    public void setUp() {
        utils = new Utils();
        utils.setupDriver();

        driver = utils.getDriver();

        magPage = new MagPage(driver);
    }

    @Test
    public void userCanOpenMagazineMaterial() {
        magPage.open();
        assertTrue(magPage.hasMagazinePageContent(), "После открытия раздела не отображаются материалы журнала");
        assertTrue(magPage.hasClickableMaterials(), "В журнале не найдены кликабельные статьи или новости");

        System.out.println("Первый найденный материал:");
        System.out.println(magPage.getFirstMaterialText());

        magPage.openFirstMaterial();
        assertTrue(magPage.hasMaterialContent(), "После открытия материала не отображается его содержимое");

        System.out.println("Открыта страница материала:");
        System.out.println(magPage.getMaterialPageTitle());
    }

    @Test
    public void userCanOpenThematicMagazineMaterials() {
        magPage.open();
        assertTrue(magPage.hasMagazinePageContent(), "После открытия раздела не отображаются материалы журнала");

        magPage.openFootballNews();
        assertTrue(magPage.hasThematicMaterials(), "После перехода в тематический раздел не отображаются материалы");
        assertTrue(magPage.hasFootballMaterials(), "После выбора темы «Футбол» не отображаются футбольные материалы");

        System.out.println("Открыт тематический раздел журнала: Футбол");
        System.out.println("Количество найденных материалов: " + magPage.getVisibleMaterialsCount());
    }

    @AfterEach
    public void tearDown() {
        if (utils != null) {
            utils.closeDriver();
        }
    }
}