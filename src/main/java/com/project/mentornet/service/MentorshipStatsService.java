package com.project.mentornet.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.project.mentornet.model.Mentorship;
import com.project.mentornet.model.MentorshipStats;
import com.project.mentornet.model.Users;
import com.project.mentornet.repository.MentorshipRepo;
import com.project.mentornet.repository.MentorshipStatsRepo;
import com.project.mentornet.repository.UserRepo;

@Service
public class MentorshipStatsService {
    private final MentorshipStatsRepo statsRepo;
    private final UserRepo userRepo;
    private final MentorshipRepo mentorRepo;
    public MentorshipStatsService(MentorshipStatsRepo statsRepo, UserRepo userRepo, MentorshipRepo mentorRepo){
        this.statsRepo = statsRepo;
        this.userRepo = userRepo;
        this.mentorRepo = mentorRepo;
    }

    private void rewardMentor(Users mentor,int points){
        MentorshipStats stats = statsRepo.findByMentor(mentor).orElseGet(()->createNewStats(mentor));

        LocalDate today = LocalDate.now();

        if(stats.getLastActiveDate()!=null){
            if(stats.getLastActiveDate().plusDays(1).equals(today)){
                stats.setCurrentStreak(stats.getCurrentStreak()+1);
            }
            else if(!stats.getLastActiveDate().equals(today)){
                stats.setCurrentStreak(1);
            }
        }
        else{
            stats.setCurrentStreak(1);
        }

        stats.setLastActiveDate(today);
        stats.setTotalPoints(stats.getTotalPoints()+points);
        stats.setLongestStreak(Math.max(stats.getLongestStreak(),stats.getCurrentStreak()));

        statsRepo.save(stats);
    }

    private MentorshipStats createNewStats(Users mentor){
        MentorshipStats stats=new MentorshipStats();
        stats.setMentor(mentor);
        stats.setCurrentStreak(0);
        stats.setLongestStreak(0);
        stats.setTotalPoints(0);
        return stats;


    }
    public void completeMentorship(Mentorship mentorship) {

        mentorship.setStatus("COMPLETED");
        mentorRepo.save(mentorship);

        rewardMentor(mentorship.getSenior(),10);
        userRepo.save(mentorship.getSenior());
    }
    public MentorshipStats getStatsForMentor(Users mentor) {
        return statsRepo.findByMentor(mentor).orElseThrow(() -> new RuntimeException("Stats not found"));
    }



}
