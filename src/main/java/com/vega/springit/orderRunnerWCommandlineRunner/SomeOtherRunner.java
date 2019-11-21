package com.vega.springit.orderRunnerWCommandlineRunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1) //decides when this component will run
public class SomeOtherRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        // do some other work
        System.out.println("SomeOtherRunner...");
    }
}
