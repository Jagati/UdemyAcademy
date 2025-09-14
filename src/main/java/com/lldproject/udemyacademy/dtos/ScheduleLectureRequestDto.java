package com.lldproject.udemyacademy.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ScheduleLectureRequestDto {

    private List<Long> lectureIds;
    private Long instructorId;
    private Long batchId;
}
