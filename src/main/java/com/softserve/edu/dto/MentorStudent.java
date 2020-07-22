package com.softserve.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MentorStudent {
    private String mentortName;
    private List<String> studentNames;
}
