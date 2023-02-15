package com.example.log_prac.service;

import com.example.log_prac.repository.AopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AopService {
    private final AopRepository aopRepository;

    public void orderItem(String itemId) {
        aopRepository.save(itemId);
    }
}
