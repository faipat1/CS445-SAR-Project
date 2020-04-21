package main;

import java.util.concurrent.atomic.AtomicInteger;

public class UniqueIDGenerator {
    static AtomicInteger atomicInteger = new AtomicInteger();
    public static int getUniqueID() {
        return atomicInteger.incrementAndGet();
    }
}
