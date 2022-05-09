package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    void getTasksTest() throws Exception{
        //Given
        List<TaskDto> tasks = List.of(new TaskDto(1L,"title","content"));
        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(tasks);
        //when & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/getTasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id",Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title",Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content",Matchers.is("content")));
    }
    @Test
    void getTaskTest()throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        Task task = new Task(1L,"title", "content");
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(dbService.getTask(1L)).thenReturn(task);
        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/getTask")
                        .queryParam("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title",Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content",Matchers.is("content")));
    }
    @Test
    void deleteTaskTest()throws Exception{
        //Given
        Task task = new Task(1L,"title", "content");
        when(dbService.saveTask(task)).thenReturn(task);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/deleteTask")
                        .queryParam("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void updateTaskTest()throws Exception{
        //Given
        Task task = new Task(1L,"title", "content");
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks/updateTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().isOk());

    }
    @Test
    void createTaskTest()throws Exception{
        //Given
        Task task = new Task(1L, "title", "content");
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks/createTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().isOk());
    }
}