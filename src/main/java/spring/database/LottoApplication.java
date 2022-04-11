package spring.database;

import com.google.common.eventbus.EventBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import spring.database.config.LottoTypeConsumer;
import spring.database.event.LotteryEvent;
import spring.database.event.LottoTipEvent;
import spring.database.event.ShowResultEvent;
import spring.database.model.impl.LottoType;
import spring.database.service.LotteryService;
import spring.database.service.impl.LotteryServiceImplementation;

import java.text.MessageFormat;
import java.util.Arrays;

@Configuration
@SpringBootApplication
public class LottoApplication {

    private static LottoType getLottoType(String arg) throws NumberFormatException {
        Integer value = Integer.valueOf(arg);
        return LottoType.fromInteger(value);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LottoApplication.class, args);
        EventBus eventBus = context.getBean(EventBus.class);
        LottoType lottoType = (args.length != 0)
                ? getLottoType(args[0])
                : lottoType.Five;
        eventBus.post(new LottoTipEvent(1, 2, 3, 4, 5));
        eventBus.post(new LottoTipEvent(1, 2, 3, 4));
        eventBus.post(new LottoTipEvent(1, 2, 3, 4, 5, 6));
        eventBus.post(new ShowResultEvent());
    }
}
