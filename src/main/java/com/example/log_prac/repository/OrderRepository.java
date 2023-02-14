package com.example.log_prac.repository;

import com.example.log_prac.trace.HelloTrace;
import com.example.log_prac.trace.TraceId;
import com.example.log_prac.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final HelloTrace trace;
    public void save(TraceId traceId, String itemId) {
        TraceStatus status = null;

        try {
            status = trace.beginSync(traceId, "OrderRepository.save()");

            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

        sleep(1_000);
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
