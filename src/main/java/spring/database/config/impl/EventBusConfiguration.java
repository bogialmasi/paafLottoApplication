package spring.database.config.impl;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventBusConfiguration {
    @Bean
    EventBus eventBus(){
        return new EventBus();
    }
}
