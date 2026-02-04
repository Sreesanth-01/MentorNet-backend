package com.project.mentornet.controller;

import com.project.mentornet.service.AIService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AI {

    private final AIService aiService;

    public AI(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/ask")
    public String askAI(@RequestBody Map<String, String> request) {

        String doubt = request.get("doubt");
        String domain = request.get("domain");

        return aiService.answerDoubt(doubt, domain);
    }
}