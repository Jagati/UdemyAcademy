package com.lldproject.udemyacademy.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import com.lldproject.udemyacademy.models.Schedule;

public class DronaUtils {

    public static String generateUniqueLectureLink(){
        return "https://scaler.com/lecture/" + UUID.randomUUID().toString();
    }

    public static Date getNextLectureStartTime(Schedule schedule, Date lastLectureTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastLectureTime);
        calendar.add(Calendar.DATE, 1);
        switch(schedule){
            case MWF_MORNING -> {
                while(calendar.DAY_OF_WEEK!=Calendar.MONDAY || calendar.DAY_OF_WEEK!=Calendar.WEDNESDAY || calendar.DAY_OF_WEEK!=Calendar.FRIDAY){
                    calendar.add(Calendar.DATE, 1);
                }
                calendar.set(Calendar.HOUR_OF_DAY, 7);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
            }
            case MWF_EVENING -> {
                while(calendar.DAY_OF_WEEK!=Calendar.MONDAY || calendar.DAY_OF_WEEK!=Calendar.WEDNESDAY || calendar.DAY_OF_WEEK!=Calendar.FRIDAY){
                    calendar.add(Calendar.DATE, 1);
                }
                calendar.set(Calendar.HOUR_OF_DAY, 21);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
            }
            case TTS_MORNING -> {
                while(calendar.DAY_OF_WEEK!=Calendar.TUESDAY || calendar.DAY_OF_WEEK!=Calendar.THURSDAY || calendar.DAY_OF_WEEK!=Calendar.SATURDAY){
                    calendar.add(Calendar.DATE, 1);
                }
                calendar.set(Calendar.HOUR_OF_DAY, 7);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);

            }
            case TTS_EVENING -> {
                while(calendar.DAY_OF_WEEK!=Calendar.TUESDAY || calendar.DAY_OF_WEEK!=Calendar.THURSDAY || calendar.DAY_OF_WEEK!=Calendar.SATURDAY){
                    calendar.add(Calendar.DATE, 1);
                }
                calendar.set(Calendar.HOUR_OF_DAY, 21);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);

            }
            default -> throw new IllegalArgumentException("Unexpected value: " + schedule);

        }
        return calendar.getTime();
    }

    public static Date getLectureEndTime(Date lectureStartTime) {
        //Add 2.5 hours to the start time
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lectureStartTime);
        calendar.add(Calendar.HOUR, 2);
        calendar.add(Calendar.MINUTE, 30);
        return calendar.getTime();
    }
}
