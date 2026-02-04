package com.project.mentornet.repository;

import java.util.List;

import com.project.mentornet.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.mentornet.model.CareerGoal;
import com.project.mentornet.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer>{
    List<Users> findAllByRoleAndCareerGoal(@Param("role") Role role,@Param("careerGoal") CareerGoal careerGoal);
}
