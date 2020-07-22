package com.softserve.edu.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.softserve.edu.dto.SprintScore;
import com.softserve.edu.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.dto.AverageScore;
import com.softserve.edu.dto.MentorStudent;
import com.softserve.edu.dto.StudentScore;
import com.softserve.edu.service.DataService;
import com.softserve.edu.service.MarathonService;

@Service
public class MarathonServiceImpl implements MarathonService {

    private final DataService dataService;

    @Autowired
    public MarathonServiceImpl(DataService dataService) {
        this.dataService = dataService;
    }

    public List<String> getStudents() {
        return dataService.getStudents().stream().map(Entity::getName).collect(Collectors.toList());
    }

    public List<String> getMentors() {
        return dataService.getMentors().stream().map(Entity::getName).collect(Collectors.toList());
    }

    public StudentScore studentResult(String studentName) {
        List<SprintScore> sprintScoreList = dataService.getSolution().stream()
                .filter(s -> dataService.getStudentById(s.getIdStudent()).getName().equals(studentName))
                .map(s -> {
                    Entity sprint = dataService.getSprintById(s.getIdSprint());
                    return new SprintScore(sprint.getName(), s.getScore());
                })
                .collect(Collectors.toList());

        return new StudentScore(studentName, sprintScoreList);
    }

    public List<StudentScore> allStudentsResult() {
        return dataService.getStudents().stream()
                .map(s -> studentResult(s.getName()))
                .collect(Collectors.toList());
    }

    public AverageScore studentAverage(String studentName) {
        double avg = studentResult(studentName).getSprintScore().stream()
                .mapToDouble(SprintScore::getScore)
                .average().orElse(Double.NaN);
        return new AverageScore(studentName, avg);
    }

    public List<AverageScore> allStudentsAverage() {
        return dataService.getStudents().stream()
                .map(s -> studentAverage(s.getName()))
                .collect(Collectors.toList());
    }

    public MentorStudent mentorStudents(String mentorName) {
        List<String> students = dataService.getCommunication().stream()
                .filter(s -> dataService.getMentorById(s.getIdMentor()).getName().equals(mentorName))
                .map(s -> dataService.getStudentById(s.getIdStudent()).getName())
                .collect(Collectors.toList());
        return new MentorStudent(mentorName, students);
    }
}
