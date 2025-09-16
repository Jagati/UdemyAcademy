package com.lldproject.udemyacademy.services;

import com.lldproject.udemyacademy.exceptions.InvalidLearnerException;
import com.lldproject.udemyacademy.models.ScheduledLecture;

import java.util.List;

public interface LearnerService {

    public List<ScheduledLecture> fetchTimeline(long learnerId) throws InvalidLearnerException;
}
