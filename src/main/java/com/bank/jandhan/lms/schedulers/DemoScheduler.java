package com.bank.jandhan.lms.schedulers;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@Component
public class DemoScheduler {

    Map<Long, Object> map = new HashMap();

    @Scheduled(fixedRate = 3000)
    public void run(){
        log.info("Started Running scheduler");
        Map<String,Object> dataMap = IntStream.range(0,1000).mapToObj(i-> "key" + i).collect(Collectors.toMap(value-> value, value->value.length()));
        map.put(System.currentTimeMillis(),dataMap);
        log.info("Finished Running scheduler");
    }
}
