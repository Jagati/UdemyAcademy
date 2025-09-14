package com.lldproject.udemyacademy.services;

import com.lldproject.udemyacademy.exceptions.InvalidScheduledLectureException;
import com.lldproject.udemyacademy.models.ScheduledLecture;

import java.util.List;

public interface ScheduledLectureService {
    public List<ScheduledLecture> rescheduleScheduledLecture(long scheduledLectureId) throws InvalidScheduledLectureException;

}
