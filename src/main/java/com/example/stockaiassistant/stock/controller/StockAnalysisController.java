package com.example.stockaiassistant.stock.controller;

import com.example.stockaiassistant.stock.service.StockAnalysisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stock")
public class StockAnalysisController {

    private final StockAnalysisService stockAnalysisService;

    public StockAnalysisController(StockAnalysisService stockAnalysisService) {
        this.stockAnalysisService = stockAnalysisService;
    }

    // 요약 테스트
    @GetMapping("/summary")
    public String getQuickSummary(@RequestParam String news) {
        return stockAnalysisService.getQuickSummary(news);
    }

    // 심층 분석 테스트
    @GetMapping("/deep-analysis")
    public String getDeepAnalysis(@RequestParam String news) {
        return stockAnalysisService.getDeepAnalysis(news);
    }
}
