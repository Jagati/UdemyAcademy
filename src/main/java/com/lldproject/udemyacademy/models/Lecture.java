package com.lldproject.udemyacademy.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Lecture extends BaseModel{

    private String name;
    private String description;
}
