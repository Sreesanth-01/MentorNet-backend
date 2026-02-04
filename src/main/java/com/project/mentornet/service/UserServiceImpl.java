package com.project.mentornet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.mentornet.model.Users;
import com.project.mentornet.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo){
        this.userRepo=userRepo;
    }

    @Override
    public List<Users> findAlignedSeniors(String role, String careerGoal){
        List<Users> AllignedSeniors = userRepo.findAllByRoleAndCareerGoal(role,careerGoal);
        return AllignedSeniors;
    }
}
