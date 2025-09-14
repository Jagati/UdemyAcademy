package com.lldproject.udemyacademy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lldproject.udemyacademy.models.Lecture;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long>{

}
