package com.lldproject.udemyacademy.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lldproject.udemyacademy.models.BatchLearner;

@Repository
public interface BatchLearnerRepository extends JpaRepository<BatchLearner, Long> {

    List<BatchLearner> findByLearner_Id(long learnerId);

    List<BatchLearner> findAllByBatch_Id(long batchId);
}
