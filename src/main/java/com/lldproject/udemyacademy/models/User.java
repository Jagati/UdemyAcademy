package com.lldproject.udemyacademy.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Data;
@Entity(name="users")
@Data
public class User extends BaseModel{

    private String name;
    private String email;
    @Enumerated
    private UserType userType;
}
