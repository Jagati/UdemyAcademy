package com.lldproject.udemyacademy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lldproject.udemyacademy.models.CommunicationLearner;

@Repository
public interface CommunicationLearnerRepository extends JpaRepository<CommunicationLearner, Long> {
}
