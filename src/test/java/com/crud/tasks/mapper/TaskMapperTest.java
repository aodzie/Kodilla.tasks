package com.crud.tasks.mapper;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TaskMapperTest {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    void mapToTaskTest(){
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        Task task = taskMapper.mapToTask(taskDto);
        assertEquals(1L, task.getId());
        assertEquals("title", task.getTitle());
        assertEquals("content", task.getContent());
    }

    @Test
    void mapToTaskDtoTest(){
        Task task = new Task(1L, "title", "content");
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        assertEquals(1L, taskDto.getId());
        assertEquals("title", taskDto.getTitle());
        assertEquals("content", taskDto.getContent());
    }

    @Test
    void mapToTaskDtoList(){
        List<Task> taskList = List.of(new Task());
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        assertThat(taskDtoList).isNotEmpty();
    }
}
