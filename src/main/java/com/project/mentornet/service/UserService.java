package com.project.mentornet.service;

import java.util.List;


import com.project.mentornet.model.CareerGoal;
import com.project.mentornet.model.Role;
import com.project.mentornet.model.Users;

public interface UserService {
    List<Users> findAlignedSeniors(Role role,CareerGoal careerGoal);
}
