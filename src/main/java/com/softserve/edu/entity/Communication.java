package com.softserve.edu.entity;

import lombok.Data;

@Data
public class Communication {
    private int idStudent;
    private int idMentor;

    public Communication(int idStudent, int idMentor) {
        this.idStudent = idStudent;
        this.idMentor = idMentor;
    }
}
