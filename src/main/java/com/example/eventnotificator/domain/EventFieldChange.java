package com.example.eventnotificator.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventFieldChange<T> {
    private T oldField;
    private T newField;
}
