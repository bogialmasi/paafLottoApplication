package spring.database.config.impl;

import ch.qos.logback.core.util.InvocationGate;
import lombok.Getter;

import java.util.*;

public class LotteryResultEvent {
    @Getter
    Set<Integer> result;

    public LotteryResultEvent(Integer... results){ // ez olyan mint egy tömb
        this.result = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(results)));
        // tömbből csinál listát amiből set lesz, nem módosítható
    }
}
