package com.test.lifung.todolist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskDetail extends TaskDto {
    private String username;
}
