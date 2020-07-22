package com.softserve.edu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.entity.Communication;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.entity.Solution;
import com.softserve.edu.service.DataService;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class DataServiceImpl implements DataService {
    private List<Entity> students;
    private List<Entity> mentors;
    private List<Entity> sprints;
    private List<Communication> communication;
    private List<Solution> solution;

    public void addStudent(String studentName) {
        Entity student = new Entity(studentName);
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }

    public void addMentor(String mentorName) {
        Entity mentor = new Entity(mentorName);
        if (mentors == null) {
            mentors = new ArrayList<>();
        }
        mentors.add(mentor);
    }

    public void addSprint(String sprintName) {
        Entity sprint = new Entity(sprintName);
        if (sprints == null) {
            sprints = new ArrayList<>();
        }
        sprints.add(sprint);
    }

    public void addCommunication(String studentName, String mentorName) {
        Entity student = students.stream()
                .filter(st -> st.getName().equals(studentName))
                .findFirst()
                .orElse(null);

        Entity mentor = mentors.stream()
                .filter(st -> st.getName().equals(mentorName))
                .findFirst()
                .orElse(null);

        if (student != null && mentor != null) {
            if (communication == null) {
                communication = new ArrayList<>();
            }
            communication.add(new Communication(student.getId(), mentor.getId()));
        }
    }

    public void addSolution(String studentName, String sprintName, int score) {
        Entity student = students.stream()
                .filter(st -> st.getName().equals(studentName))
                .findFirst()
                .orElse(null);

        Entity sprint = sprints.stream()
                .filter(st -> st.getName().equals(sprintName))
                .findFirst()
                .orElse(null);

        if (student != null && sprint != null) {
            if (solution == null) {
                solution = new ArrayList<>();
            }
            solution.add(new Solution(student.getId(), sprint.getId(), score));
        }
    }

    @Override
    public Entity getStudentById(int studentId) {
        return students.stream().filter(s -> s.getId() == studentId).findFirst().orElse(null);
    }

    @Override
    public Entity getMentorById(int mentorId) {
        return mentors.stream().filter(s -> s.getId() == mentorId).findFirst().orElse(null);
    }

    @Override
    public Entity getSprintById(int sprintId) {
        return sprints.stream().filter(s -> s.getId() == sprintId).findFirst().orElse(null);
    }
}
