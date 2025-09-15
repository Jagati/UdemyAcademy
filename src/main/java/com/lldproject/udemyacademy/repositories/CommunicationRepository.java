package com.lldproject.udemyacademy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lldproject.udemyacademy.models.Communication;

@Repository
public interface CommunicationRepository extends JpaRepository<Communication, Long> {
}
