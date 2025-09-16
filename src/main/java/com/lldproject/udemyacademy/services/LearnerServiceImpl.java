package com.lldproject.udemyacademy.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lldproject.udemyacademy.exceptions.InvalidLearnerException;
import com.lldproject.udemyacademy.models.Batch;
import com.lldproject.udemyacademy.models.BatchLearner;
import com.lldproject.udemyacademy.models.Learner;
import com.lldproject.udemyacademy.models.ScheduledLecture;
import com.lldproject.udemyacademy.repositories.BatchLearnerRepository;
import com.lldproject.udemyacademy.repositories.BatchRepository;
import com.lldproject.udemyacademy.repositories.LearnerRepository;
import com.lldproject.udemyacademy.repositories.LectureRepository;
import com.lldproject.udemyacademy.repositories.ScheduledLectureRepository;
import com.lldproject.udemyacademy.utils.DronaUtils;

@Service
public class LearnerServiceImpl implements LearnerService {
    private BatchRepository batchRepository;
    private BatchLearnerRepository batchLearnerRepository;
    private LearnerRepository learnerRepository;
    private LectureRepository lectureRepository;
    private ScheduledLectureRepository scheduledLectureRepository;

    @Autowired
    public LearnerServiceImpl(BatchRepository batchRepository, BatchLearnerRepository batchLearnerRepository, LearnerRepository learnerRepository, LectureRepository lectureRepository, ScheduledLectureRepository scheduledLectureRepository){
        this.batchLearnerRepository = batchLearnerRepository;
        this.batchRepository = batchRepository;
        this.learnerRepository = learnerRepository;
        this.lectureRepository = lectureRepository;
        this.scheduledLectureRepository = scheduledLectureRepository;
    }

    @Override
    public List<ScheduledLecture> fetchTimeline(long learnerId) throws InvalidLearnerException{
        Optional<Learner> learnerOp = learnerRepository.findById(learnerId);
        if(learnerOp.isEmpty()){
            throw new InvalidLearnerException("learner is not found.");
        }
        //Find list of batches the learner has been part of
        List<BatchLearner> batchLearnerList = batchLearnerRepository.findByLearner_Id(learnerId);
        List<ScheduledLecture> listOfAllScheduledLectures = new ArrayList<>();
        for(BatchLearner batchLearner: batchLearnerList){
            if(batchLearner.exitDate==null){
                Batch currentBatch = batchLearner.getBatch();
                Date startDate = batchLearner.getEntryDate();
                List<ScheduledLecture> scheduledLecturesForCurrentBatch = scheduledLectureRepository.findByBatch_Id(currentBatch.getId());
                listOfAllScheduledLectures.addAll(DronaUtils.selectScheduledLecturesOnAndAfterStartDate(startDate, scheduledLecturesForCurrentBatch));
            }
            else{
                Batch pastBatch = batchLearner.getBatch();
                Date startDate = batchLearner.getEntryDate();
                Date endDate = batchLearner.getExitDate();
                List<ScheduledLecture> scheduledLecturesForPastBatch = scheduledLectureRepository.findByBatch_Id(pastBatch.getId());
                listOfAllScheduledLectures.addAll(DronaUtils.selectScheduledLecturesBetweenStartDateAndEndDate(startDate, endDate, scheduledLecturesForPastBatch));
            }
        }
        //WAY 2: Just call this function below:
        //return scheduledLectureRepository.findAllLecturesForLearner(learnerId);
        return listOfAllScheduledLectures;
    }

}
