package com.example.log_prac.trace;

import org.junit.jupiter.api.Test;

class HelloTraceTest {
    @Test
    void begin_end() {
        HelloTrace trace = new HelloTrace();
        TraceStatus status = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status.getTraceId(), "hello2");
        trace.end(status2);
        trace.end(status);
    }

    @Test
    void begin_exception() {
        HelloTrace trace = new HelloTrace();
        TraceStatus status = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status.getTraceId(), "hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status, new IllegalStateException());
    }
}