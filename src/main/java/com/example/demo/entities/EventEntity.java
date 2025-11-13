package com.example.demo.entities;

import com.example.demo.enums.StatusEventEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity {
    private Long idEvent;
    private String nameEvent;
    private String descriptionEvent;
    private LocalDateTime datetimeEvent;
    private Integer durationEvent;
    private Double priceEvent;
    private StatusEventEnum statusEvent;
    private Long idVenue;
}
