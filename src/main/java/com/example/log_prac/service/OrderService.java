package com.example.log_prac.service;

import com.example.log_prac.repository.OrderRepository;
import com.example.log_prac.trace.HelloTrace;
import com.example.log_prac.trace.TraceId;
import com.example.log_prac.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final HelloTrace trace;

    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus status = null;

        try {
            status = trace.beginSync(traceId, "OrderService.orderItem()");
            orderRepository.save(traceId.createNextId(), itemId);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
