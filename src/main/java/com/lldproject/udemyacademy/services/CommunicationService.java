package com.lldproject.udemyacademy.services;

import com.lldproject.udemyacademy.exceptions.InvalidBatchException;
import com.lldproject.udemyacademy.exceptions.InvalidUserException;
import com.lldproject.udemyacademy.exceptions.UnAuthorizedAccessException;
import com.lldproject.udemyacademy.models.Communication;

public interface CommunicationService {

    public Communication broadcastMessage(long batchId, long userId, String message) throws InvalidBatchException, InvalidUserException, UnAuthorizedAccessException;
}
