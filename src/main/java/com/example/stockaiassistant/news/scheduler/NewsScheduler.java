package com.example.stockaiassistant.news.scheduler;

import com.example.stockaiassistant.news.client.NaverNewsClient;
import com.example.stockaiassistant.news.dto.NaverNewsItem;
import com.example.stockaiassistant.news.dto.NaverNewsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NewsScheduler {

    private static final Logger log = LoggerFactory.getLogger(NewsScheduler.class);

    private final NaverNewsClient naverNewsClient;

    public NewsScheduler(NaverNewsClient naverNewsClient) {
        this.naverNewsClient = naverNewsClient;
    }

    @Scheduled(fixedRate = 10000)
    public void collectNews() {
        log.info("[자동 수집] 네이버 뉴스 수집 시작");

        NaverNewsResponse response = naverNewsClient.searchNews("삼성전자");

        if (response != null && response.items() != null) {
            for (NaverNewsItem item : response.items()) {
                log.info("뉴스 제목: {}", item.title());
            }
        }

    }
}
