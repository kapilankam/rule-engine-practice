package com.kapil.ruleengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.kapil", lazyInit = true)
public class RuleEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuleEngineApplication.class, args);
    }

}
