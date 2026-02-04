package com.project.mentornet.service;

import java.util.List;

import com.project.mentornet.model.Users;

public interface UserService {
    List<Users> findAlignedSeniors(String role,String careerGoal);
}
