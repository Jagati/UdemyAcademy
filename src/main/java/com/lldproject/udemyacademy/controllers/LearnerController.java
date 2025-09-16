package com.lldproject.udemyacademy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lldproject.udemyacademy.dtos.FetchTimelineRequestDto;
import com.lldproject.udemyacademy.dtos.FetchTimelineResponseDto;
import com.lldproject.udemyacademy.dtos.ResponseStatus;
import com.lldproject.udemyacademy.models.ScheduledLecture;
import com.lldproject.udemyacademy.services.LearnerService;
@Controller
public class LearnerController {
    private LearnerService learnerService;

    @Autowired
    public LearnerController(LearnerService learnerService){
        this.learnerService = learnerService;
    }

    public FetchTimelineResponseDto fetchTimeline(FetchTimelineRequestDto requestDto){
        FetchTimelineResponseDto responseDto = new FetchTimelineResponseDto();
        try{
            List<ScheduledLecture> scheduledLectures = learnerService.fetchTimeline(requestDto.getLearnerId());
            responseDto.setLectures(scheduledLectures);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
