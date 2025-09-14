package com.lldproject.udemyacademy.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.lldproject.udemyacademy.utils.DronaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lldproject.udemyacademy.exceptions.InvalidScheduledLectureException;
import com.lldproject.udemyacademy.models.ScheduledLecture;
import com.lldproject.udemyacademy.repositories.BatchRepository;
import com.lldproject.udemyacademy.repositories.InstructorRepository;
import com.lldproject.udemyacademy.repositories.LectureRepository;
import com.lldproject.udemyacademy.repositories.ScheduledLectureRepository;

@Service
public class ScheduledLectureServiceImpl implements ScheduledLectureService{
    private BatchRepository batchRepository;
    private InstructorRepository instructorRepository;
    private LectureRepository lectureRepository;
    private ScheduledLectureRepository scheduledLectureRepository;
    @Autowired
    public ScheduledLectureServiceImpl(BatchRepository batchRepository, InstructorRepository instructorRepository, LectureRepository lectureRepository, ScheduledLectureRepository scheduledLectureRepository){
        this.batchRepository = batchRepository;
        this.instructorRepository = instructorRepository;
        this.lectureRepository = lectureRepository;
        this.scheduledLectureRepository = scheduledLectureRepository;
    }
    @Override
    public List<ScheduledLecture> rescheduleScheduledLecture(long scheduledLectureId) throws InvalidScheduledLectureException{
        Optional<ScheduledLecture> scheduledLectureOp = scheduledLectureRepository.findById(scheduledLectureId);
        if(scheduledLectureOp.isEmpty()){
            throw new InvalidScheduledLectureException("No such scheduled lecture found.");
        }
        ScheduledLecture cancelledLecture = scheduledLectureOp.get();
        Date cancelledLectureDate = cancelledLecture.getLectureStartTime();
        //Get list of all scheduled lectures on and after this date
        List<ScheduledLecture> allScheduledLectures = new ArrayList<>();
        allScheduledLectures.add(cancelledLecture);
        List<ScheduledLecture> futureScheduledLectures = scheduledLectureRepository.findByLectureStartTimeAfter(cancelledLectureDate);
        allScheduledLectures.addAll(futureScheduledLectures);
        return DronaUtils.recheduleLectures(allScheduledLectures);
    }

}
