package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();

   // @Query
   // List<Task> retrieveTasks(@Param("ID") int id);

    @Override
    Task save (Task task);

    @Override
    Optional<Task> findById(Long id);

    @Override
    void deleteById (Long id);

}
