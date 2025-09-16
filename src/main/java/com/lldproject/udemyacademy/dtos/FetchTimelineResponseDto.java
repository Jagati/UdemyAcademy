package com.lldproject.udemyacademy.dtos;

import com.lldproject.udemyacademy.models.ScheduledLecture;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class FetchTimelineResponseDto {

    private List<ScheduledLecture> lectures;
    private ResponseStatus responseStatus;
}
