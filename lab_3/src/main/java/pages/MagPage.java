package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MagPage extends Page {

    private final String materialCards =
            "//a[contains(@href, '/mag/novosti/') and not(@href='/mag/novosti/')]"
                    + " | //a[contains(@href, '/mag/statji/') and not(@href='/mag/statji/')]";

    private final String magazineContent =
            "//*[contains(normalize-space(.), 'Новости')]"
                    + " | //*[contains(normalize-space(.), 'Статьи')]"
                    + " | //*[contains(normalize-space(.), 'Новость')]";

    private final String footballNews =
            "//a[contains(@href, '/mag/novosti/football/') or contains(normalize-space(.), 'Футбол')]";

    private final String hockeyNews =
            "//a[contains(@href, '/mag/novosti/hockey/') or contains(normalize-space(.), 'Хоккей')]";

    private final String materialContent =
            "//*[contains(normalize-space(.), 'спорт')]"
                    + " | //*[contains(normalize-space(.), 'матч')]"
                    + " | //*[contains(normalize-space(.), 'Футбол')]"
                    + " | //*[contains(normalize-space(.), 'Хоккей')]"
                    + " | //*[contains(normalize-space(.), 'Новость')]"
                    + " | //*[contains(normalize-space(.), 'Статья')]";

    public MagPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        openPath("/mag/");
    }

    public boolean hasMagazinePageContent() {
        return hasMaterials();
    }

    public boolean hasMaterials() {
        return isElementPresent(materialCards)
                || isElementPresent(magazineContent);
    }

    public boolean hasFootballNewsLink() {
        return isElementPresent(footballNews);
    }

    public boolean hasHockeyNewsLink() {
        return isElementPresent(hockeyNews);
    }

    public void openFirstMaterial() {
        List<WebElement> materials = findElementsByXpath(materialCards);

        if (materials.isEmpty()) {
            throw new IllegalStateException("Не найдены статьи или новости журнала");
        }

        scrollTo(materials.get(0));
        materials.get(0).click();
    }

    public boolean hasMaterialContent() {
        return isElementPresent("//h1")
                && isElementPresent(materialContent);
    }

    public void openFootballNews() {
        openPath("/mag/novosti/football/");
    }

    public void openHockeyNews() {
        openPath("/mag/novosti/hockey/");
    }

    public boolean hasThematicMaterials() {
        return hasMaterials();
    }
}