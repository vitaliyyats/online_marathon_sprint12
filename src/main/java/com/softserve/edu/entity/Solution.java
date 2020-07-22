package com.softserve.edu.entity;

import lombok.Data;

@Data
public class Solution {
    private int idStudent;
    private int idSprint;
    private int score;

    public Solution(int idStudent, int idSprint, int score) {
        this.idStudent = idStudent;
        this.idSprint = idSprint;
        this.score = score;
    }
}
