package com.lldproject.udemyacademy.dtos;

import com.lldproject.udemyacademy.models.ScheduledLecture;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ScheduleLecturesResponseDto {
    private List<ScheduledLecture> scheduledLectures;
    private ResponseStatus responseStatus;
}
