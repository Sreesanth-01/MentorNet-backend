package com.project.mentornet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MentorshipStatsResponse {
    private int totalPoints;
    private int currentStreak;
    private int longestStreak;
}
