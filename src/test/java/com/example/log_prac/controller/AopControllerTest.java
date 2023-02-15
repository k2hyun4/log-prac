package com.example.log_prac.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AopControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void requestSuccess() throws Exception {
        mockMvc.perform(get("/aop/request")
                        .param("id", "newId"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void requestOccurException() {
        assertThatThrownBy(() -> mockMvc.perform(get("/aop/request").param("id", "ex"))
                .andDo(print())
                .andExpect(status().is5xxServerError()))
                .hasCause(new IllegalStateException("예외 발생!"));
    }
}