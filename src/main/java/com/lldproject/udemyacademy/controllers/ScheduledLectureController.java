package com.lldproject.udemyacademy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lldproject.udemyacademy.dtos.RescheduleScheduledLectureRequestDto;
import com.lldproject.udemyacademy.dtos.RescheduleScheduledLectureResponseDto;
import com.lldproject.udemyacademy.dtos.ResponseStatus;
import com.lldproject.udemyacademy.models.ScheduledLecture;
import com.lldproject.udemyacademy.services.ScheduledLectureService;
@Controller
public class ScheduledLectureController {
    private final ScheduledLectureService scheduledLectureService;
    @Autowired
    public ScheduledLectureController(ScheduledLectureService scheduledLectureService){
        this.scheduledLectureService=scheduledLectureService;
    }

    public RescheduleScheduledLectureResponseDto rescheduleScheduledLecture(RescheduleScheduledLectureRequestDto requestDto) {
        RescheduleScheduledLectureResponseDto responseDto = new RescheduleScheduledLectureResponseDto();
        try{
            List<ScheduledLecture> rescheduledLectures = scheduledLectureService.rescheduleScheduledLecture(requestDto.getScheduledLectureId());
            responseDto.setScheduledLectures(rescheduledLectures);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
