package com.cacaofriendsshop.scraping.dto;

import com.cacaofriendsshop.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@EqualsAndHashCode
@ToString
@Getter
@AllArgsConstructor
public class ProductScarpingDto {

    private String title;
    private String price;
    private String characterType;
    private String detailPageUrl;
    private String thumbnailImageUrl;

    public Product toProduct(List<String> detailPageImageUrls) {
        return Product.builder()
                .title(title)
                .price(price)
                .characterType(characterType)
                .thumbnailImageUrl(thumbnailImageUrl)
                .soldCount(0)
                .detailPageImageUrls(detailPageImageUrls)
                .build();
    }
}
