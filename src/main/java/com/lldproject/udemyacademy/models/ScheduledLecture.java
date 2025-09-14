package com.lldproject.udemyacademy.models;

import lombok.Data;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Data
@Entity
public class ScheduledLecture extends BaseModel{
    @ManyToOne
    private Lecture lecture;
    @ManyToOne
    private Batch batch;
    @ManyToOne
    private Instructor instructor;
    private Date lectureStartTime;
    private Date lectureEndTime;
    private String lectureLink;
}
