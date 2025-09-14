package com.lldproject.udemyacademy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lldproject.udemyacademy.models.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
