package com.project.mentornet.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int skills_id;
    @ElementCollection
    private List<String> programmingLagnuages;
    private int projectsDone;
    private int problemsSolved;
}
