package com.test.lifung.todolist.repository;

import com.test.lifung.todolist.dto.Status;
import com.test.lifung.todolist.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserUsername(String username);

    List<Task> findByUserUsernameAndStatus(String username, Status status);

    Page<Task> findAllByOrderByUserUsernameAsc(Pageable pageable);
}
