package com.lldproject.udemyacademy.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BroadcastMessageRequestDto {
    private String message;
    private long userId;
    private long batchId;
}
