package com.project.mentornet.service;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AIService {

    private final String OPENROUTER_URL = "https://openrouter.ai/api/v1/chat/completions";
    @Value("${openrouter.api.key}")
    private String API_KEY;

    public String answerDoubt(String doubt, String domain) {

        RestTemplate restTemplate = new RestTemplate();

        String prompt = """
        You are an AI guidance assistant for college students in the domain of %s.

        Student doubt / topic: "%s"

        You should ONLY provide:
          1. Recommended online courses (2-3)
          2. Suggested mini-projects to practice (2-3)
          3. List of relevant languages/skills to learn

        Return ONLY in this JSON format (no extra text):

        {
          "recommendedCourses": ["...","..."],
          "suggestedProjects": ["...","..."],
          "skillsToLearn": ["...","..."]
        }
        """.formatted(domain, doubt);

        Map<String, Object> request = new HashMap<>();
        request.put("model", "openai/gpt-4o-mini");

        Map<String, String> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);

        request.put("messages", List.of(message));

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("HTTP-Referer", "http://localhost:8080");
        headers.set("X-Title", "MentorNet");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        Map response = restTemplate.postForObject(OPENROUTER_URL, entity, Map.class);

        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
        Map<String, Object> firstChoice = choices.get(0);
        Map<String, Object> messageObj = (Map<String, Object>) firstChoice.get("message");

        return (String) messageObj.get("content");
    }
}
