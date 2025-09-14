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
}
