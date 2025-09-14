package com.lldproject.udemyacademy.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lldproject.udemyacademy.exceptions.InvalidBatchException;
import com.lldproject.udemyacademy.exceptions.InvalidInstructorException;
import com.lldproject.udemyacademy.exceptions.InvalidLectureException;
import com.lldproject.udemyacademy.models.Batch;
import com.lldproject.udemyacademy.models.Instructor;
import com.lldproject.udemyacademy.models.Lecture;
import com.lldproject.udemyacademy.models.ScheduledLecture;
import com.lldproject.udemyacademy.repositories.BatchRepository;
import com.lldproject.udemyacademy.repositories.InstructorRepository;
import com.lldproject.udemyacademy.repositories.LectureRepository;
import com.lldproject.udemyacademy.repositories.ScheduledLectureRepository;
import com.lldproject.udemyacademy.utils.DronaUtils;

@Service
public class LectureServiceImpl implements LectureService {

    private final BatchRepository batchRepository;
    private final InstructorRepository instructorRepository;
    private final LectureRepository lectureRepository;
    private final ScheduledLectureRepository scheduledLectureRepository;

    public LectureServiceImpl(BatchRepository batchRepository, InstructorRepository instructorRepository, LectureRepository lectureRepository, ScheduledLectureRepository scheduledLectureRepository){
        this.batchRepository = batchRepository;
        this.instructorRepository = instructorRepository;
        this.lectureRepository = lectureRepository;
        this.scheduledLectureRepository = scheduledLectureRepository;
    }

    @Override
    public List<ScheduledLecture> scheduleLectures(List<Long> lectureIds, Long instructorId, Long batchId) throws InvalidLectureException, InvalidInstructorException, InvalidBatchException{
        //Get lectures
        List<Lecture> lectures = lectureRepository.findAllById(lectureIds);
        if(lectures.size()!=lectureIds.size()){
            throw new InvalidLectureException("Few lectures not found.");
        }
        Optional<Instructor> instructorOp = instructorRepository.findById(instructorId);
        if(instructorOp.isEmpty()){
            throw new InvalidInstructorException("Instructor not found.");
        }
        Instructor instructor = instructorOp.get();
        Optional<Batch> batchOp = batchRepository.findById(batchId);
        if(batchOp.isEmpty()){
            throw new InvalidBatchException("Batch not found.");
        }
        Batch batch = batchOp.get();

        Optional<ScheduledLecture> scheduledLectureOp=scheduledLectureRepository.findTopByBatch_IdOrderByLectureEndTimeDesc(batchId);
        Date lastLectureTime;
        if(scheduledLectureOp.isEmpty()){
            lastLectureTime = new Date();
        }
        else{
            lastLectureTime = scheduledLectureOp.get().getLectureEndTime();
        }
        List<ScheduledLecture> scheduledLectures = new ArrayList<>();
        for(Lecture lecture: lectures){
            ScheduledLecture scheduledLecture = new ScheduledLecture();
            scheduledLecture.setBatch(batch);
            scheduledLecture.setInstructor(instructor);
            scheduledLecture.setLecture(lecture);
            scheduledLecture.setLectureStartTime(DronaUtils.getNextLectureStartTime(batch.getSchedule(), lastLectureTime));
            scheduledLecture.setLectureEndTime(DronaUtils.getLectureEndTime(scheduledLecture.getLectureStartTime()));
            scheduledLecture.setLectureLink(DronaUtils.generateUniqueLectureLink());
            scheduledLectures.add(scheduledLectureRepository.save(scheduledLecture));
        }
        return scheduledLectures;
    }
}
