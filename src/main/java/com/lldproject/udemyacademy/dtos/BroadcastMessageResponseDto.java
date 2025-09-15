package com.lldproject.udemyacademy.dtos;

import com.lldproject.udemyacademy.models.Communication;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BroadcastMessageResponseDto {

    private ResponseStatus status;
    private Communication communication;
}
