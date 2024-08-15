package com.example.eventnotificator.dto;

import com.example.eventnotificator.domain.EventFieldChange;
import com.example.eventnotificator.domain.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventChangeKafkaMessage implements Serializable {
    private Long eventId;
    private Long ownerId;
    private Long changeById;

    private EventFieldChange<String> name;
    private EventFieldChange<Integer> maxPlaces;
    private EventFieldChange<LocalDateTime> date;
    private EventFieldChange<Integer> cost;
    private EventFieldChange<Integer> duration;
    private EventFieldChange<Integer> locationId;
    private EventFieldChange<EventStatus> status;
}
