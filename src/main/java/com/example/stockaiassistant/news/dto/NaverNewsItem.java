package com.example.stockaiassistant.news.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// 뉴스 1개
@JsonIgnoreProperties(ignoreUnknown = true)
public record NaverNewsItem(
        String title,
        String originallink,
        String link,
        String description,
        String pubDate
) {
}
