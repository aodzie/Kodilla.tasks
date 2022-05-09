package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbServiceTest {
    @InjectMocks
    private DbService dbService;
    @Mock
    private TaskRepository repository;

    @Test
    void getAllTasksTest(){
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L, "title", "content");
        taskList.add(task);
        //When
        when(repository.findAll()).thenReturn(taskList);
        List<Task> dbServiceList = dbService.getAllTasks();
        //Then
        assertEquals(1, dbServiceList.size());
    }
    @Test
    void getTaskTest() throws TaskNotFoundException {
        //Given
        Long taskId = 1L;
        Task task = new Task(taskId, "task1", "content1");
        //When
        when(repository.findById(taskId)).thenReturn(Optional.of(task));
        Task foundTask = dbService.getTask(taskId);
        //Then
        assertEquals(task, foundTask);
    }
    @Test
    void saveTaskTest(){
        //When
        Task task = new Task(1L, "title", "content");
        //When
        when(repository.save(task)).thenReturn(task);
        Task newTask = dbService.saveTask(task);
        //Then
        assertEquals(task, newTask);

    }
    @Test
    void deleteTaskTest(){
        //When
        Long taskId = 1L;
        Task task = new Task(taskId, "title", "content");
        //When
        dbService.saveTask(task);
        dbService.deleteTask(taskId);
        assertThat(repository.count()).isEqualTo(0L);
    }
}
