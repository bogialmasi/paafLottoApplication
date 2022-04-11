package spring.database.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.contect.request.RequestContectHoler;
import org.springframework.web.contect.request.ServletResuqestAttributes;
import spring.database.model.LogData;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class LotteryEventPublisher {

    @Autowired
    private LotteryEvent lotteryEventPublisher;

    public void publishEvent(String msg){
        Map<String, String> dataMap = new LinkedHashMap<>();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        dataMap.put("five", request.getHeader("five"));
        dataMap.put("six", request.getHeader("six"));
        LotteryEventPublisher.publishEvent(new LotteryEvent<LogData>(LogData.builder().data(dataMap).build()));
    }
}
