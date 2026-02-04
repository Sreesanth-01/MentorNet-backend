package com.project.mentornet.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mentornet.model.Mentorship;

public interface MentorshipRepo extends JpaRepository<Mentorship,Integer> {

    
}
