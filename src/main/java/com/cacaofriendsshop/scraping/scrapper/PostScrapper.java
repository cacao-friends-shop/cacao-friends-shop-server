package com.cacaofriendsshop.scraping.scrapper;

import com.cacaofriendsshop.post.domain.Post;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PostScrapper {

    private static final String BASE_URL = "https://store.kakaofriends.com/kr/profile/5?tab=story";
    private static final String CHARACTER_TYPE = "네오";

    public Set<Post> crawlStory() {
        Set<Post> posts = new LinkedHashSet<>();
        Scrapper scrapper = new Scrapper();
        try {
            scrapper.get(BASE_URL);
            int contentSize = 0;
            while (true) {
                Document doc = Jsoup.parse(scrapper.getPageSource());
                Elements articles = doc.select("article");
                for (Element article : articles) {
                    List<String> imageUrls = article.select(".media-slide__Image-sc-1mcr0rn-1").stream()
                            .map(element -> element.attr("src"))
                            .collect(Collectors.toList());
                    String updatedDate = article.select(".header__DisplayDate-sc-1uyrtg9-7").text();
                    String title = article.select(".contents__Title-sc-1b0iw5u-5").text();
                    String content = article.select(".contents__SubCopy-sc-1b0iw5u-6").text();
                    Post post = Post.builder()
                            .title(title)
                            .characterType(CHARACTER_TYPE)
                            .updatedDate(updatedDate)
                            .content(content)
                            .imageUrls(imageUrls)
                            .build();
                    posts.add(post);
                }
                if (contentSize == posts.size()) {
                    break;
                } else {
                    contentSize = posts.size();
                }
                JavascriptExecutor jse = scrapper.createJavascriptExecutor();
                jse.executeScript("window.scrollBy(0,20000)", "");
                Thread.sleep(1000);
            }
            return posts;
        } catch (Exception e) {
            throw new RuntimeException("크롤링 실패");
        }
    }

}
