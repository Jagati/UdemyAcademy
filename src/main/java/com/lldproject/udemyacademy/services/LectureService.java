package com.lldproject.udemyacademy.services;

import com.lldproject.udemyacademy.exceptions.InvalidBatchException;
import com.lldproject.udemyacademy.exceptions.InvalidInstructorException;
import com.lldproject.udemyacademy.exceptions.InvalidLectureException;
import com.lldproject.udemyacademy.models.ScheduledLecture;

import java.util.List;

public interface LectureService {

    List<ScheduledLecture> scheduleLectures(List<Long> lectureIds, Long instructorId, Long batchId) throws InvalidLectureException, InvalidInstructorException, InvalidBatchException;
}
