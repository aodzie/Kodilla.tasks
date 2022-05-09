package com.crud.tasks;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskApplicationTest {

    @Test
    void taskApplicationTest(){
        SpringApplication.run(TasksApplication.class);
    }
}