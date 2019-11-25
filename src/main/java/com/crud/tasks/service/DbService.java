package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;


@Service
public class DbService {
    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }
    public long getCountTasks() {
        return repository.count();
    }


    public List<Task> getTasksId() {
        return repository.retrieveTasks(3);
    }

    public Task saveTask (final Task task) {
        return repository.save(task);
    }

    public Optional<Task> getTask (final Long id) {
        return repository.findById(id);
    }

    public void deleteTask (final Long id) {
        repository.deleteById(id);
    }
}