package com.lldproject.udemyacademy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lldproject.udemyacademy.dtos.ResponseStatus;
import com.lldproject.udemyacademy.dtos.ScheduleLectureRequestDto;
import com.lldproject.udemyacademy.dtos.ScheduleLecturesResponseDto;
import com.lldproject.udemyacademy.models.ScheduledLecture;
import com.lldproject.udemyacademy.services.LectureService;
@Controller
public class LectureController {
    private LectureService lectureService;
    @Autowired
    public LectureController(LectureService lectureService){
        this.lectureService=lectureService;
    }

    public ScheduleLecturesResponseDto scheduleLectures(ScheduleLectureRequestDto requestDto) {
        ScheduleLecturesResponseDto responseDto = new ScheduleLecturesResponseDto();
        try{
            List<ScheduledLecture> scheduledLectures = lectureService.scheduleLectures(requestDto.getLectureIds(), requestDto.getInstructorId(), requestDto.getBatchId());
            responseDto.setScheduledLectures(scheduledLectures);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
