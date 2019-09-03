package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NamedQuery(
        name = "Task.retrieveTasks",
        query = "FROM tasks  WHERE id = :ID"
)

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="content")
    private String content;

}
