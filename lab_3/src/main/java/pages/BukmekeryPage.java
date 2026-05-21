package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BukmekeryPage extends Page {

    public static class BookmakerInfo {
        private final String name;
        private final double rating;

        public BookmakerInfo(String name, double rating) {
            this.name = name;
            this.rating = rating;
        }

        public String getName() {
            return name;
        }

        public double getRating() {
            return rating;
        }

        @Override
        public String toString() {
            return name + " — " + rating;
        }
    }

    private final String pageTitle = "//h1[contains(normalize-space(.), 'Ставки на спорт онлайн')]";
    private final String bookmakerCards = "//a[string-length(@href) > 1 and contains(normalize-space(.), 'Рейтинг')]";
    private final String fonbetCard = "//a[contains(@href, '/fonbet/')]";
    private final String ratingInfo = "//*[contains(normalize-space(.), 'Рейтинг')]";
    private final String reviewsBlock = "//*[contains(normalize-space(.), 'Рейтинг и отзывы')]"
                    + " | //*[contains(normalize-space(.), 'Отзывы')]";

    private final String openAllReviewsButton = "//*[contains(normalize-space(.), 'Смотреть все отзывы')]";
    private final String freebetButton = "//*[contains(normalize-space(.), 'Получить фрибет')]";
    private final String registrationForm =
            "//*[contains(normalize-space(.), 'Регистрация')]"
                    + " | //*[contains(normalize-space(.), 'Зарегистрироваться')]"
                    + " | //*[contains(normalize-space(.), 'Телефон')]"
                    + " | //*[contains(normalize-space(.), 'номер телефона')]";

    public BukmekeryPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        openPath("/bukmekery/");
    }

    public boolean hasBookmakersPageContent() {
        return isElementPresent(pageTitle)
                && hasBookmakerCardsForComparison();
    }

    public boolean hasBookmakerCardsForComparison() {
        return getBookmakersInfo().size() >= 2;
    }

    public boolean bookmakerCardsContainRatings() {
        return !getBookmakersInfo().isEmpty();
    }

    public List<BookmakerInfo> getBookmakersInfo() {
        List<BookmakerInfo> bookmakers = new ArrayList<>();

        for (WebElement card : findElementsByXpath(bookmakerCards)) {
            String text = card.getText().trim();

            if (text.isEmpty()) {
                continue;
            }

            String name = extractBookmakerName(card, text);
            double rating = extractRating(text);

            if (name != null && !name.isBlank() && rating > 0) {
                addOrUpdateBookmaker(bookmakers, new BookmakerInfo(name, rating));
            }
        }

        return bookmakers;
    }

    public BookmakerInfo getBestRatedBookmaker() {
        return getBookmakersInfo().stream()
                .max(Comparator.comparingDouble(BookmakerInfo::getRating))
                .orElseThrow(() -> new IllegalStateException("Нет букмекеров с рейтингом для сравнения"));
    }

    public BookmakerInfo getWorstRatedBookmaker() {
        return getBookmakersInfo().stream()
                .min(Comparator.comparingDouble(BookmakerInfo::getRating))
                .orElseThrow(() -> new IllegalStateException("Нет букмекеров с рейтингом для сравнения"));
    }

    public boolean hasFonbetCard() {
        return isElementPresent(fonbetCard);
    }

    public void openFonbetCard() {
        if (!hasFonbetCard()) {
            throw new IllegalStateException("Карточка Fonbet не найдена");
        }

        jsClickByXpath(fonbetCard);
    }

    public boolean hasBookmakerRatingInfo() {
        return isElementPresent(ratingInfo);
    }

    public boolean hasReviewsSection() {
        return isElementPresent(reviewsBlock);
    }

    public void openReviewsSection() {
        if (!hasReviewsSection()) {
            throw new IllegalStateException("Раздел отзывов не найден");
        }

        scrollTo(findByXpath(reviewsBlock));
    }

    public boolean hasOpenAllReviewsButton() {
        return isElementPresent(openAllReviewsButton);
    }

    public boolean hasReviewText() {
        try {
            return !getFirstReviewText().isBlank();
        } catch (IllegalStateException exception) {
            return false;
        }
    }

    public String getFirstReviewText() {
        String pageText = driver.findElement(By.tagName("body")).getText();

        int reviewsStart = pageText.indexOf("Рейтинг и отзывы");
        int reviewsEnd = pageText.indexOf("Смотреть все отзывы");

        if (reviewsStart == -1 || reviewsEnd == -1 || reviewsEnd <= reviewsStart) {
            throw new IllegalStateException("Блок коротких отзывов не найден");
        }

        String reviewsText = pageText.substring(reviewsStart, reviewsEnd);
        String[] lines = reviewsText.split("\\R");

        for (int i = 0; i < lines.length; i++) {
            String value = lines[i].trim();

            if (value.matches("\\d,[0-9]")) {
                for (int j = i + 1; j < lines.length; j++) {
                    String reviewText = lines[j].trim();

                    if (!reviewText.isBlank() && reviewText.length() > 20) {
                        return reviewText.length() > 300
                                ? reviewText.substring(0, 300) + "..."
                                : reviewText;
                    }
                }
            }
        }

        throw new IllegalStateException("Текст отзыва не найден");
    }

    public boolean hasFreebetButton() {
        return isElementPresent(freebetButton);
    }

    public void openFreebetRegistration() {
        if (!hasFreebetButton()) {
            throw new IllegalStateException("Кнопка получения фрибета не найдена");
        }

        Set<String> oldWindows = driver.getWindowHandles();

        jsClickByXpath(freebetButton);

        switchToNewWindowIfOpened(oldWindows);
    }

    public boolean hasRegistrationForm() {
        return isElementPresent(registrationForm);
    }

    private void switchToNewWindowIfOpened(Set<String> oldWindows) {
        Set<String> newWindows = driver.getWindowHandles();

        if (newWindows.size() <= oldWindows.size()) {
            return;
        }

        for (String windowHandle : newWindows) {
            if (!oldWindows.contains(windowHandle)) {
                driver.switchTo().window(windowHandle);
                return;
            }
        }
    }

    private String extractBookmakerName(WebElement card, String text) {
        String nameFromText = extractNameFromText(text);

        if (nameFromText != null) {
            return nameFromText;
        }

        String href = card.getAttribute("href");

        if (href == null || href.isBlank()) {
            return null;
        }

        return href.replace("https://sravni.bet/", "")
                .replace("http://sravni.bet/", "")
                .replace("/bukmekery/", "")
                .replace("/", "")
                .trim();
    }

    private String extractNameFromText(String text) {
        for (String line : text.split("\\R")) {
            String value = line.trim();

            if (value.isEmpty()
                    || value.toLowerCase().contains("рейтинг")
                    || value.matches("\\d+(\\.\\d+|,\\d+)?")) {
                continue;
            }

            return value;
        }

        return null;
    }

    private double extractRating(String text) {
        String normalizedText = text.replace(',', '.');

        Pattern ratingPattern = Pattern.compile(
                "Рейтинг\\s*(\\d+(?:\\.\\d+)?)",
                Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
        );

        Matcher ratingMatcher = ratingPattern.matcher(normalizedText);

        if (ratingMatcher.find()) {
            return Double.parseDouble(ratingMatcher.group(1));
        }

        Pattern fallbackPattern = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher fallbackMatcher = fallbackPattern.matcher(normalizedText);

        while (fallbackMatcher.find()) {
            double value = Double.parseDouble(fallbackMatcher.group(1));

            if (value >= 1.0 && value <= 5.0) {
                return value;
            }
        }

        return -1;
    }

    private void addOrUpdateBookmaker(List<BookmakerInfo> bookmakers, BookmakerInfo candidate) {
        for (int i = 0; i < bookmakers.size(); i++) {
            BookmakerInfo existing = bookmakers.get(i);

            if (existing.getName().equalsIgnoreCase(candidate.getName())) {
                if (candidate.getRating() > existing.getRating()) {
                    bookmakers.set(i, candidate);
                }

                return;
            }
        }

        bookmakers.add(candidate);
    }
}