package com.softserve.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StudentScore {
    private String studentName;
    private List<SprintScore> sprintScore;
}
