package com.lldproject.udemyacademy.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lldproject.udemyacademy.models.ScheduledLecture;

@Repository
public interface ScheduledLectureRepository extends JpaRepository<ScheduledLecture, Long>{
    @Query("SELECT sl FROM ScheduledLecture sl WHERE sl.batch.id = :batchId ORDER BY sl.lectureEndTime DESC LIMIT 1")
    Optional<ScheduledLecture> findTopByBatch_IdOrderByLectureEndTimeDesc(@Param("batchId")long batchId);
    List<ScheduledLecture> findByLectureStartTimeAfter(Date cancelledLectureDate);
    List<ScheduledLecture> findByBatch_Id(long id);

    //Another way to get all lectures list for a learner using the query below:
    @Query("""
    SELECT sl FROM ScheduledLecture sl
    JOIN BatchLearner bl ON bl.batch = sl.batch
    WHERE bl.learner.id = :learnerId
    AND sl.lectureStartTime >= bl.entryDate
    AND (bl.exitDate IS NULL OR sl.lectureStartTime <= bl.exitDate)
    order by sl.lectureStartTime
    """)
    List<ScheduledLecture> findAllLecturesForLearner(@Param("learnerId") Long learnerId);
}
