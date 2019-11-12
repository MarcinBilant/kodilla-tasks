package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService dbService;


    @Test
    public void shouldGetEmptyTasks() throws Exception {
        //given
        List<Task> tasks = new ArrayList<>();
        when(dbService.getAllTasks()).thenReturn(tasks);

        //when & then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));

    }
    @Test
    public void shouldGetTasks() throws Exception {
        //given
        List<Task> tasks = new ArrayList<>();
        Task task1 = new Task(1L,"title1","content1");
        Task task2 = new Task (2L,"title2", "content2");
        tasks.add(task1);
        tasks.add(task2);

        List<TaskDto> tasksDto = new ArrayList<>();
        TaskDto taskDto1 = new TaskDto(1L,"title1","content1");
        TaskDto taskDto2 = new TaskDto (2L,"title2", "content2");
        tasksDto.add(taskDto1);
        tasksDto.add(taskDto2);

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);

        //when&then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].title",is("title1")))
                .andExpect(jsonPath("$[0].content",is("content1")))
                .andExpect(jsonPath("$[1].id",is(2)))
                .andExpect(jsonPath("$[1].title",is("title2")))
                .andExpect(jsonPath("$[1].content",is("content2")));

    }
    @Test
    public void shouldGetTask() throws Exception {
        //given
        Task task1 = new Task(1L,"title1","content1");
        Optional<Task> task1Optional = Optional.of(task1);

        TaskDto taskDto1 = new TaskDto(1L,"title1","content1");

        when(dbService.getTask(1L)).thenReturn(task1Optional);
        when(taskMapper.mapToTaskDto(task1)).thenReturn(taskDto1);

        //when&then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("title1")))
                .andExpect(jsonPath("$.content",is("content1")));

    }
    @Test
    public void shouldDeleteTask() throws Exception {
        //given
        Task task1 = new Task(1L,"title1","content1");

        //when&then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //given
        Task task1 = new Task(1L,"title1","content1");
        TaskDto taskDto1 = new TaskDto(1L,"title1","content1");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto1);

        when(taskMapper.mapToTask(taskDto1)).thenReturn(task1);
        when(dbService.saveTask(task1)).thenReturn(task1);
        when(taskMapper.mapToTaskDto(task1)).thenReturn(taskDto1);

        //when&then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id",is(1)))
               // .andExpect(jsonPath("$.title",is("title1")))
              // .andExpect(jsonPath("$.content",is("content1")));

    }

    @Test
    public void shouldCreateTask() throws Exception {
        //given
        Task task1 = new Task(1L,"title1","content1");
        TaskDto taskDto1 = new TaskDto(1L,"title1","content1");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto1);

        when(dbService.saveTask(task1)).thenReturn(task1);
        when(taskMapper.mapToTask(taskDto1)).thenReturn(task1);

        //when&then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
        // .andExpect(jsonPath("$.id",is(1)))
        // .andExpect(jsonPath("$.title",is("title1")))
        // .andExpect(jsonPath("$.content",is("content1")));
    }

}
