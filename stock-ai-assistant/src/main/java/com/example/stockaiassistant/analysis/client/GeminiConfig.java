package com.example.stockaiassistant.analysis.client;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeminiConfig {

    @Value("${gemini.api-key}")
    private String apiKey;

    @Value("${gemini.summary-model-name}")
    private String summaryModelName;

    @Value("${gemini.deep-analysis-model-name}")
    private String deepAnalysisModelName;

    // 요약용 Flash Lite 모델
    @Bean
    public ChatLanguageModel summaryModel() {
        return GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName(summaryModelName)
                .temperature(0.7)
                .build();
    }

    // 심층분석용 Pro 모델
    @Bean
    public ChatLanguageModel deepAnalysisModel() {
        return GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName(deepAnalysisModelName)
                .temperature(0.3)
                .build();
    }

}
