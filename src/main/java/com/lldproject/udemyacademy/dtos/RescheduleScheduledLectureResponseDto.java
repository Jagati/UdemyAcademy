package com.lldproject.udemyacademy.dtos;

import com.lldproject.udemyacademy.models.ScheduledLecture;
import lombok.Data;

import java.util.List;

@Data
public class RescheduleScheduledLectureResponseDto {
    private List<ScheduledLecture> scheduledLectures;

    private ResponseStatus responseStatus;
}
