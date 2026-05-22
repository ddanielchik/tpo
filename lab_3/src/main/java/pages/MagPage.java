package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MagPage extends Page {

    private final String magazineContent =
            "//*[contains(normalize-space(.), 'Новости')]"
                    + " | //*[contains(normalize-space(.), 'Статьи')]"
                    + " | //*[contains(normalize-space(.), 'Новость')]"
                    + " | //*[contains(normalize-space(.), 'Журнал')]";

    private final String materialCards =
            "//a[contains(@href, '/mag/novosti/') and not(@href='/mag/novosti/')]"
                    + " | //a[contains(@href, '/mag/statji/') and not(@href='/mag/statji/')]";

    private final String footballNews =
            "//a[contains(@href, '/mag/novosti/football/') "
                    + "or contains(normalize-space(.), 'Футбол')]";

    private final String hockeyNews =
            "//a[contains(@href, '/mag/novosti/hockey/') "
                    + "or contains(normalize-space(.), 'Хоккей')]";

    private final String materialContent =
            "//*[contains(normalize-space(.), 'спорт')]"
                    + " | //*[contains(normalize-space(.), 'матч')]"
                    + " | //*[contains(normalize-space(.), 'Футбол')]"
                    + " | //*[contains(normalize-space(.), 'Хоккей')]"
                    + " | //*[contains(normalize-space(.), 'Новость')]"
                    + " | //*[contains(normalize-space(.), 'Статья')]"
                    + " | //*[contains(normalize-space(.), 'Опубликовано')]"
                    + " | //*[contains(normalize-space(.), 'Дата')]";

    private final String relatedMaterials =
            "//a[contains(@href, '/mag/novosti/') and not(@href='/mag/novosti/')]"
                    + " | //a[contains(@href, '/mag/statji/') and not(@href='/mag/statji/')]"
                    + " | //*[contains(normalize-space(.), 'Читайте также')]"
                    + " | //*[contains(normalize-space(.), 'Похожие материалы')]";

    public MagPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        openPath("/mag/");
    }

    public void openFootballNews() {
        openPath("/mag/novosti/football/");
    }

    public void openHockeyNews() {
        openPath("/mag/novosti/hockey/");
    }

    public boolean hasMagazinePageContent() {
        return hasMaterials();
    }

    public boolean hasMaterials() {
        return isElementPresent(materialCards)
                || isElementPresent(magazineContent);
    }

    public boolean hasClickableMaterials() {
        return !findElementsByXpath(materialCards).isEmpty();
    }

    public boolean hasFootballNewsLink() {
        return isElementPresent(footballNews);
    }

    public boolean hasHockeyNewsLink() {
        return isElementPresent(hockeyNews);
    }

    public boolean hasFootballMaterials() {
        return isTextPresent("Футбол")
                && hasMaterials();
    }

    public boolean hasHockeyMaterials() {
        return isTextPresent("Хоккей")
                && hasMaterials();
    }

    public boolean hasThematicMaterials() {
        return hasMaterials();
    }

    public int getVisibleMaterialsCount() {
        return findElementsByXpath(materialCards).size();
    }

    public String getFirstMaterialText() {
        List<WebElement> materials = findElementsByXpath(materialCards);

        if (materials.isEmpty()) {
            throw new IllegalStateException("Не найдены статьи или новости журнала");
        }

        return materials.get(0).getText().trim();
    }

    public void openFirstMaterial() {
        List<WebElement> materials = findElementsByXpath(materialCards);

        if (materials.isEmpty()) {
            throw new IllegalStateException("Не найдены статьи или новости журнала");
        }

        WebElement material = materials.get(0);

        System.out.println("Открываем материал журнала:");
        System.out.println(material.getText());
        System.out.println("href = " + material.getAttribute("href"));

        scrollTo(material);
        jsClick(material);
    }

    public boolean hasMaterialContent() {
        return hasMaterialTitle()
                && hasMaterialMainText();
    }

    public boolean hasMaterialTitle() {
        return isElementPresent("//h1");
    }

    public boolean hasMaterialMainText() {
        return isElementPresent(materialContent);
    }

    public String getMaterialPageTitle() {
        return findByXpath("//h1").getText().trim();
    }

    public boolean hasRelatedMaterials() {
        return isElementPresent(relatedMaterials);
    }

    public void openFirstRelatedMaterial() {
        List<WebElement> materials = findElementsByXpath(relatedMaterials);

        if (materials.isEmpty()) {
            throw new IllegalStateException("Не найдены связанные материалы журнала");
        }

        WebElement material = materials.get(0);

        System.out.println("Открываем связанный материал:");
        System.out.println(material.getText());
        System.out.println("href = " + material.getAttribute("href"));

        scrollTo(material);
        jsClick(material);
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
    }
}