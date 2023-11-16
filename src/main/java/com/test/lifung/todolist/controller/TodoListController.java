package com.test.lifung.todolist.controller;

import com.test.lifung.todolist.dto.*;
import com.test.lifung.todolist.service.TaskService;
import com.test.lifung.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TodoListController extends BaseAPI {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public BaseResponse<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto data = userService.createUser(userDto);
        BaseResponse<UserDto> response = new BaseResponse<>(StatusCode.SUCCESS);
        response.setData(data);
        return response;
    }

    @GetMapping("/tasks/grouped-by-user")
    public BaseResponse<PagingResponse<Map<String, List<TaskDetail>>>> getAllTasks(@RequestParam(defaultValue = "0") int page) {
        PagingResponse<Map<String, List<TaskDetail>>> tasks  = taskService.getTasksGroupedByUserName(page);
        BaseResponse<PagingResponse<Map<String, List<TaskDetail>>>> response = new BaseResponse<>(StatusCode.SUCCESS);
        response.setData(tasks);
        return response;
    }

    @PostMapping("/tasks/{user_name}")
    public BaseResponse<TaskDto> createTask(@PathVariable("user_name") String userName,
                                            @RequestBody TaskDto taskDto) {
        TaskDto data = taskService.createTask(userName, taskDto);
        BaseResponse<TaskDto> response = new BaseResponse<>(StatusCode.SUCCESS);
        response.setData(data);
        return response;
    }

    @PutMapping("/tasks/{user_name}/{task_id}")
    public BaseResponse<TaskDto> doneTask(@PathVariable("user_name") String userName,
                                          @PathVariable("task_id") Long id) {
        TaskDto data = taskService.doneTask(userName, id);
        BaseResponse<TaskDto> response = new BaseResponse<>(StatusCode.SUCCESS);
        response.setData(data);
        return response;
    }

    @GetMapping("/tasks/{user_name}")
    public BaseResponse<List<TaskDto>> getTasksByUser(@PathVariable("user_name") String userName) {
        List<TaskDto> data = taskService.getTasksByUser(userName);
        BaseResponse<List<TaskDto>> response = new BaseResponse<>(StatusCode.SUCCESS);
        response.setData(data);
        return response;
    }

    @GetMapping("/tasks/{user_name}/status")
    public BaseResponse<List<TaskDto>> getTasksByUserAndStatus(@PathVariable("user_name") String userName,
                                                               @RequestParam(required = false) Status status) {
        List<TaskDto> data = taskService.getTasksByUserAndStatus(userName, status);
        BaseResponse<List<TaskDto>> response = new BaseResponse<>(StatusCode.SUCCESS);
        response.setData(data);
        return response;
    }
}
