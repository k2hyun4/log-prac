package com.example.log_prac.controller;


import com.example.log_prac.RequestDto;
import com.example.log_prac.service.AopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AopController {
    private final AopService aopService;

    @GetMapping("/aop/request")
    public String request(RequestDto dto) {
        aopService.orderItem(dto.getId());

        return "ok";
    }
}
