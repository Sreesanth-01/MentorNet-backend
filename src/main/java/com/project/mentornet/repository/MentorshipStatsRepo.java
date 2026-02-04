package com.project.mentornet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.mentornet.model.MentorshipStats;
import com.project.mentornet.model.Users;

@Repository
public interface MentorshipStatsRepo extends JpaRepository<MentorshipStats,Integer> {
    Optional<MentorshipStats> findByMentor(Users mentor);
    
}
