package spring.database.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import spring.database.model.LogData;

@Component
public class LotteryEventHandler {

    @EventListener
    @Async
    public void handleEvent(LotteryEvent<LogData> lotteryEvent){
        System.out.println(new ObjectMapper()
        .writeWithDefaultPrettyPrinter()
        .writeValueAsString(lotteryEvent.getData()));
    }
}
