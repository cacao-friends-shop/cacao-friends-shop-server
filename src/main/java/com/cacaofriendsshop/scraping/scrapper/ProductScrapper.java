package com.cacaofriendsshop.scraping.scrapper;

import com.cacaofriendsshop.product.domain.Product;
import com.cacaofriendsshop.scraping.dto.ProductScarpingDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductScrapper {

    private static final String BASE_URL = "https://store.kakaofriends.com";
    private static final String PRODUCT_TARGET_URL = "/kr/products/category/character?categorySeq=7&sort=createDatetime,desc";
    private static final String CHARACTER_TYPE = "프로도";


    public List<Product> createProductsByCrawling() {
        Set<ProductScarpingDto> productScarpingDtos = crawlProductScarpingDto();
        return crawlDetailProduct(productScarpingDtos);
    }

    public Set<ProductScarpingDto> crawlProductScarpingDto() {
        Set<ProductScarpingDto> productScarpingDtos = new LinkedHashSet<>();
        Scrapper scrapper = new Scrapper();
        try {
            scrapper.get(BASE_URL + PRODUCT_TARGET_URL);
            int contentSize = 0;
            while (true) {
                Document doc = Jsoup.parse(scrapper.getPageSource());
                Elements products = doc.select(".item__Li-sc-5t2pho-0");
                for (Element product : products) {
                    String detailPageLink = product.select("a").attr("href");
                    String productName = product.select(".item__ItemTitle-sc-5t2pho-2").text();
                    Elements pTags = product.select(".item__Price-sc-5t2pho-3").select("span");
                    String index1 = pTags.get(1).text();
                    String index2 = pTags.get(2).text();
                    String productPrice = index1.contains("%") ? index2 : index1;
                    String productImg = product.select("img").attr("src");
                    if (productImg == null || productImg.isEmpty()) {
                        continue;
                    }
                    ProductScarpingDto productScarpingDto = ProductScarpingDto.builder()
                            .title(productName)
                            .price(productPrice)
                            .thumbnailImageUrl(productImg)
                            .detailPageUrl(detailPageLink)
                            .characterType(CHARACTER_TYPE)
                            .build();
                    productScarpingDtos.add(productScarpingDto);
                }
                if (contentSize == productScarpingDtos.size()) {
                    break;
                } else {
                    contentSize = productScarpingDtos.size();
                }
                JavascriptExecutor jse = scrapper.createJavascriptExecutor();
                jse.executeScript("window.scrollBy(0,20000)", "");
                Thread.sleep(1000);
            }
            return productScarpingDtos;
        } catch (Exception e) {
            throw new RuntimeException("크롤링 실패");
        }
    }

    public List<Product> crawlDetailProduct(Set<ProductScarpingDto> productScarpingDtos) {
        List<Product> products = new ArrayList<>();
        Scrapper scrapper = new Scrapper();
        for (ProductScarpingDto productScarpingDto : productScarpingDtos) {
            String detailPageUrl = productScarpingDto.getDetailPageUrl();
            scrapper.get(BASE_URL + detailPageUrl);
            Document doc = Jsoup.parse(scrapper.getPageSource());
            Elements elements = doc.select(".react-swipeable-view-container").select("img");
            List<String> detailImgUrls = elements.stream()
                    .map(element -> element
                            .attr("src"))
                    .collect(Collectors.toList());
            products.add(productScarpingDto.toProduct(detailImgUrls));
        }
        return products;
    }

}
