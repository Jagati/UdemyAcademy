package com.lldproject.udemyacademy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lldproject.udemyacademy.models.Learner;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, Long>{
}
