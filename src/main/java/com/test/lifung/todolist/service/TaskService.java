package com.test.lifung.todolist.service;

import com.test.lifung.todolist.dto.PagingResponse;
import com.test.lifung.todolist.dto.Status;
import com.test.lifung.todolist.dto.TaskDetail;
import com.test.lifung.todolist.dto.TaskDto;
import com.test.lifung.todolist.entity.Task;
import com.test.lifung.todolist.entity.User;
import com.test.lifung.todolist.repository.UserRepository;
import com.test.lifung.todolist.exception.NotFoundEntityException;
import com.test.lifung.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    public static final int PAGE_SIZE = 10;
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;


    public TaskDto createTask(String userName, TaskDto taskDto) {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new NotFoundEntityException("userName is not exists");
        }

        Task task = new Task();
        task.setDescription(taskDto.getDescription());
        task.setUser(user);
        task.setStatus(Status.CREATED);
        Task savedTask = taskRepository.save(task);
        return mapTaskToTaskDto(savedTask);
    }

    public TaskDto doneTask(String userName, Long taskId) {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new NotFoundEntityException("userName is not exists");
        }

        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(Status.DONE);
            Task updatedTask = taskRepository.save(task);

            return mapTaskToTaskDto(updatedTask);
        }

        throw new NotFoundEntityException("TaskId is not exists");
    }

    public PagingResponse<Map<String, List<TaskDetail>>> getTasksGroupedByUserName(int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<Task> taskPage = taskRepository.findAllByOrderByUserUsernameAsc(pageable);
        Map<String, List<TaskDetail>> taskDtoList = taskPage.getContent()
                .stream()
                .map(this::mapTaskToTaskDetail)
                .collect(Collectors.groupingBy(TaskDetail::getUsername));

        PagingResponse<Map<String, List<TaskDetail>>> result = new PagingResponse<>();
        result.setTotalRecords(taskPage.getTotalElements());
        result.setTotalPages(taskPage.getTotalPages());
        result.setContent(taskDtoList);

        return result;
    }
    public List<TaskDto> getTasksByUser(String username) {
        List<Task> tasks = taskRepository.findByUserUsername(username);
        return tasks.stream()
                .map(this::mapTaskToTaskDto)
                .toList();
    }

    public List<TaskDto> getTasksByUserAndStatus(String userName, Status status) {
        List<Task> tasks = taskRepository.findByUserUsernameAndStatus(userName, status);
        return tasks.stream()
                .map(this::mapTaskToTaskDto)
                .toList();
    }

    private TaskDto mapTaskToTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setDescription(task.getDescription());
        taskDto.setStatus(task.getStatus());
        return taskDto;
    }

    private TaskDetail mapTaskToTaskDetail(Task task) {
        TaskDetail detail = new TaskDetail();
        detail.setId(task.getId());
        detail.setDescription(task.getDescription());
        detail.setStatus(task.getStatus());
        detail.setUsername(task.getUser().getUsername());
        return detail;
    }
}
