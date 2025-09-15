package com.lldproject.udemyacademy.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lldproject.udemyacademy.adapter.EmailAdapter;
import com.lldproject.udemyacademy.adapter.WhatsappAdapter;
import com.lldproject.udemyacademy.exceptions.InvalidBatchException;
import com.lldproject.udemyacademy.exceptions.InvalidUserException;
import com.lldproject.udemyacademy.exceptions.UnAuthorizedAccessException;
import com.lldproject.udemyacademy.models.Batch;
import com.lldproject.udemyacademy.models.BatchLearner;
import com.lldproject.udemyacademy.models.Communication;
import com.lldproject.udemyacademy.models.CommunicationLearner;
import com.lldproject.udemyacademy.models.Learner;
import com.lldproject.udemyacademy.models.User;
import com.lldproject.udemyacademy.models.UserType;
import com.lldproject.udemyacademy.repositories.BatchLearnerRepository;
import com.lldproject.udemyacademy.repositories.BatchRepository;
import com.lldproject.udemyacademy.repositories.CommunicationLearnerRepository;
import com.lldproject.udemyacademy.repositories.CommunicationRepository;
import com.lldproject.udemyacademy.repositories.LearnerRepository;
import com.lldproject.udemyacademy.repositories.UserRepository;

@Service
public class CommunicationServiceImpl implements CommunicationService {
    private final BatchLearnerRepository batchLearnerRepository;
    private final BatchRepository batchRepository;
    private final CommunicationLearnerRepository communicationLearnerRepository;
    private final CommunicationRepository communicationRepository;
    private final LearnerRepository learnerRepository;
    private final UserRepository userRepository;
    private final EmailAdapter emailAdapter;
    private final WhatsappAdapter whatsappAdapter;
    @Autowired
    public CommunicationServiceImpl(BatchLearnerRepository batchLearnerRepository, BatchRepository batchRepository, CommunicationLearnerRepository communicationLearnerRepository, CommunicationRepository communicationRepository, LearnerRepository learnerRepository, UserRepository userRepository, EmailAdapter emailAdapter, WhatsappAdapter whatsappAdapter){
        this.batchLearnerRepository = batchLearnerRepository;
        this.batchRepository = batchRepository;
        this.communicationLearnerRepository = communicationLearnerRepository;
        this.communicationRepository = communicationRepository;
        this.learnerRepository = learnerRepository;
        this.userRepository = userRepository;
        this.emailAdapter = emailAdapter;
        this.whatsappAdapter = whatsappAdapter;
    }

    @Override
    public Communication broadcastMessage(long batchId, long userId, String message) throws InvalidBatchException, InvalidUserException, UnAuthorizedAccessException{
        Optional<User> userOp = userRepository.findById(userId);
        if(userOp.isEmpty()){
            throw new InvalidUserException("User not found");
        }
        User user = userOp.get();
        if(user.getUserType()!=UserType.ADMIN){
            throw new UnAuthorizedAccessException("User not authorized. User must be admin.");
        }
        Optional<Batch> batchOp = batchRepository.findById(batchId);
        if(batchOp.isEmpty()){
            throw new InvalidBatchException("Batch not found.");
        }
        Batch batch = batchOp.get();
        //Save the communication
        Communication communication = new Communication();
        communication.setBatch(batch);
        communication.setSentBy(user);
        communication.setMessage(message);
        communicationRepository.save(communication);

        List<Learner> learners = new ArrayList<>();
        List<BatchLearner> batchLearners = batchLearnerRepository.findAllByBatch_Id(batchId);
        //Filter the batchLearners list based on exit date
        Date today = new Date();
        for(BatchLearner batchLearner: batchLearners){
            if(batchLearner.exitDate==null){
                learners.add(batchLearner.getLearner());
            }
        }
        //Save everything to communicationLearner repository
        for(Learner learner: learners){
            CommunicationLearner communicationLearner = new CommunicationLearner();
            communicationLearner.setCommunication(communication);
            try{
                whatsappAdapter.sendWhatsappMessage(learner.getPhoneNumber(), message);
                communicationLearner.setWhatsappDelivered(true);
            }
            catch(Exception e){
                communicationLearner.setWhatsappDelivered(false);
            }
            try{
                emailAdapter.sendEmail(learner.getEmail(), message);
                communicationLearner.setEmailDelivered(true);
            }
            catch(Exception e){
                communicationLearner.setEmailDelivered(false);
            }
            communicationLearner.setLearner(learner);
            communicationLearnerRepository.save(communicationLearner);
        }
        return communication;
    }

}
