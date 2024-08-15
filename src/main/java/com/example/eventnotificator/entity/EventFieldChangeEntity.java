package com.example.eventnotificator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "changes", schema = "event-manage")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventFieldChangeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "old_field")
    private String oldField;

    @Column(name = "new_field")
    private String newField;
}
