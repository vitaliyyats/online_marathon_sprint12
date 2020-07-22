package com.softserve.edu.entity;

import lombok.Data;

@Data
public class Entity {
    private int id; // must be unique
    private String name;

    private static int idGenerator = 1;

    public Entity(String name) {
        id = idGenerator++;
        this.name = name;
    }
}
