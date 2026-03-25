package com.example.stockaiassistant.news.client;

import com.example.stockaiassistant.news.dto.NaverNewsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class NaverNewsClient {

    private final RestClient restClient;

    // 생성자에서 RestClient의 베이스 설정(헤더 등)을 미리 고정
    public NaverNewsClient(
            @Value("${naver.client-id}") String clientId,
            @Value("${naver.client-secret}") String clientSecret) {

        this.restClient = RestClient.builder()
                .baseUrl("https://openapi.naver.com/v1/search/news.json")
                .defaultHeader("X-Naver-Client-Id", clientId)
                .defaultHeader("X-Naver-Client-Secret", clientSecret)
                .build();
    }

    // 네이버 뉴스를 긁어오는 메서드
    public NaverNewsResponse searchNews(String query) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("query", query) // 검색어 (예: 삼섬전자)
                        .queryParam("display",10) // 10개
                        .queryParam("sort", "date") // 최신순 정렬
                        .build())
                .retrieve()
                .body(NaverNewsResponse.class); // JSON 문자열 그대로 반환받음
    }

}
