package com.nicefish.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.nicefish.search.*")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class NiceFishSearchApplication {
    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "true");
        SpringApplication.run(NiceFishSearchApplication.class, args);
        System.out.println(">>>>>>>>>>> NiceFish Search started >>>>>>>>>>>");
    }
}
