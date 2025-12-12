package com.codegnan.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class AIService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final WebClient webClient;

    public AIService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://generativelanguage.googleapis.com/v1beta")
                .build();
    }

    public String generateAnalysis(String resumeText) {

        String prompt =
                "Analyze this resume and provide:\n" +
                "• Strengths\n" +
                "• Missing skills\n" +
                "• Grammar improvements\n" +
                "• Suitable job roles\n\n" +
                "Resume:\n" + resumeText;

        String requestBody = """
                {
                  "contents": [{
                    "parts": [{
                      "text": "%s"
                    }]
                  }]
                }
                """.formatted(prompt.replace("\"", "'"));

        String endpoint =
                "/models/gemini-1.5-flash:generateContent?key=" + apiKey;

        try {
            return webClient.post()
                    .uri(endpoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .onErrorResume(e -> Mono.just("Gemini API Error: " + e.getMessage()))
                    .block();
        } catch (Exception e) {
            return "AI Service Error: " + e.getMessage();
        }
    }
}
