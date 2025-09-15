package com.lldproject.udemyacademy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lldproject.udemyacademy.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
