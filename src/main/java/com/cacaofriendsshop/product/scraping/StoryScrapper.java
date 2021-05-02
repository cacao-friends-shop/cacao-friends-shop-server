package com.cacaofriendsshop.product.scraping;

import com.cacaofriendsshop.product.domain.Story;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StoryScrapper {

    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH = "C:/Users/ebseu/Desktop/chromedriver.exe";
    private static final String BASE_URL = "https://store.kakaofriends.com/kr/profile/5?tab=story";
    private static final String CHARACTER_TYPE = "네오";

    private WebDriver driver;

    public StoryScrapper() {
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        driver = new ChromeDriver();
    }

    public static void main(String[] args) {
        StoryScrapper storyScrapper = new StoryScrapper();
        storyScrapper.crawlStory();
    }

    public Set<Story> crawlStory() {
        Set<Story> stories = new LinkedHashSet<>();
        try {
            driver.get(BASE_URL);
            int contentSize = 0;
            while (true) {
                Document doc = Jsoup.parse(driver.getPageSource());
                Elements articles = doc.select("article");
                for (Element article : articles) {
                    List<String> imageUrls = article.select(".media-slide__Image-sc-1mcr0rn-1").stream()
                            .map(element -> element.attr("src"))
                            .collect(Collectors.toList());
                    String updatedDate = article.select(".header__DisplayDate-sc-1uyrtg9-7").text();
                    String title = article.select(".contents__Title-sc-1b0iw5u-5").text();
                    String content = article.select(".contents__SubCopy-sc-1b0iw5u-6").text();
                    Story story = Story.builder()
                            .title(title)
                            .characterType(CHARACTER_TYPE)
                            .updatedDate(updatedDate)
                            .content(content)
                            .imageUrls(imageUrls)
                            .build();
                    stories.add(story);
                }
                if (contentSize == stories.size()) {
                    break;
                } else {
                    contentSize = stories.size();
                }
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                jse.executeScript("window.scrollBy(0,20000)", "");
                Thread.sleep(1000);
            }
            return stories;
        } catch (Exception e) {
            throw new RuntimeException("크롤링 실패");
        }
    }

}
