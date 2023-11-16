package com.test.lifung.todolist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private String description;
    private Status status;
}
