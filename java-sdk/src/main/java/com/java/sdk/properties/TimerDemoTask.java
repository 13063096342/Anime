package com.java.sdk.properties;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

/**
 * @author chenfh
 * @date 2020-03-16 17:58
 */
@Component
@Order(value = 1)
@EnableAsync
public class TimerDemoTask extends TimerTask implements CommandLineRunner {
    @Override
    public void run() {
       // System.out.println("---------   定时器打印------");
    }

    @Override
    public void run(String... strings) throws Exception {
       // new Timer().schedule(TimerDemoTask.this, 0, 10 * 1000);
    }
}
