package com.softserve.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AverageScore {
    private String studentName;
    private double avgScore;
}
