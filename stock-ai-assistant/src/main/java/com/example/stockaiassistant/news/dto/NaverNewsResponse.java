package com.example.stockaiassistant.news.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

// 뉴스 리스트
@JsonIgnoreProperties(ignoreUnknown = true)
public record NaverNewsResponse(
        List<NaverNewsItem> items
) {
}
