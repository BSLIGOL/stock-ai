package com.example.stockaiassistant.stock.service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StockAnalysisService {

    private final ChatLanguageModel summaryModel;
    private final ChatLanguageModel deepAnalysisModel;

    public StockAnalysisService(
            @Qualifier("summaryModel") ChatLanguageModel summaryModel,
            @Qualifier("deepAnalysisModel") ChatLanguageModel deepAnalysisModel) {
        this.summaryModel = summaryModel;
        this.deepAnalysisModel = deepAnalysisModel;
    }

    public String getQuickSummary(String newsContent) {
        return summaryModel.generate(newsContent + "\n\n위 주식 뉴스를 한 줄로 요약해 줘");
    }

    public String getDeepAnalysis(String announcementDetail) {
        return deepAnalysisModel.generate(announcementDetail + "\n\n위 공시의 리스크 요인과 기회 요인을 분석해 줘");
    }
}
