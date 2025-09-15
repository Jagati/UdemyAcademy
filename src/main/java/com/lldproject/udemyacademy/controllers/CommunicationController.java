package com.lldproject.udemyacademy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lldproject.udemyacademy.dtos.BroadcastMessageRequestDto;
import com.lldproject.udemyacademy.dtos.BroadcastMessageResponseDto;
import com.lldproject.udemyacademy.dtos.ResponseStatus;
import com.lldproject.udemyacademy.models.Communication;
import com.lldproject.udemyacademy.services.CommunicationService;
@Controller
public class CommunicationController {
    private final CommunicationService communicationService;
    @Autowired
    public CommunicationController(CommunicationService communicationService){
        this.communicationService = communicationService;
    }

    public BroadcastMessageResponseDto broadcastMessage(BroadcastMessageRequestDto requestDto) {
        BroadcastMessageResponseDto responseDto = new BroadcastMessageResponseDto();
        try{
            Communication communication = communicationService.broadcastMessage(requestDto.getBatchId(), requestDto.getUserId(), requestDto.getMessage());
            responseDto.setCommunication(communication);
            responseDto.setStatus(ResponseStatus.SUCCESS);

        }
        catch(Exception e){
            responseDto.setStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
