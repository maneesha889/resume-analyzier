package com.codegnan.controller;

import com.codegnan.service.AIService;
import com.codegnan.service.TextExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private AIService aiService;

    // API health check
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Resume Analyzer API is running!");
    }

    // General instructions
    @GetMapping
    public ResponseEntity<String> getInstructions() {
        return ResponseEntity.ok("Use POST /api/resume/upload to upload a resume and get AI analysis.");
    }

    // Resume upload + analysis
    @PostMapping("/upload")
    public ResponseEntity<String> analyzeResume(@RequestParam("file") MultipartFile file) {

        try {
            // Extract text from uploaded resume
            String text = TextExtractor.extractText(file);
            if (text == null || text.isBlank()) {
                return ResponseEntity.badRequest().body("Could not extract text from the uploaded file.");
            }

            // Send to AI service (Gemini)
            String aiResponse = aiService.generateAnalysis(text);

            return ResponseEntity.ok(aiResponse);

        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body("Error analyzing resume: " + e.getMessage());
        }
    }
}
