package spring.database.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class LotteryEvent<T> extends ApplicationEvent {

    private T data;

    public LotteryEvent(T event) {
        super(event);
        this.data = event;
    }
}
