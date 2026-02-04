package com.project.mentornet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.mentornet.dto.MentorshipStatsResponse;
import com.project.mentornet.model.MentorshipStats;
import com.project.mentornet.model.Users;
import com.project.mentornet.repository.UserRepo;
import com.project.mentornet.service.MentorshipStatsService;

@RestController
@RequestMapping("/api/mentor-stats")
public class MentorshipStatsController {

    private final MentorshipStatsService statsService;
    private final UserRepo userRepo;

    public MentorshipStatsController(MentorshipStatsService statsService, UserRepo userRepo ) {
        this.statsService = statsService;
        this.userRepo = userRepo;
    }

    @GetMapping("/{mentorId}")
    public ResponseEntity<MentorshipStatsResponse> getStats(@PathVariable int mentorId) {
        Users mentor = userRepo.findById(mentorId)
                .orElseThrow(() -> new RuntimeException("Mentor not found"));

        MentorshipStats stats = statsService.getStatsForMentor(mentor);

        MentorshipStatsResponse response =
                new MentorshipStatsResponse(
                        stats.getTotalPoints(),
                        stats.getCurrentStreak(),
                        stats.getLongestStreak()
                );

        return ResponseEntity.ok(response);
    }
}
