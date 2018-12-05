package cn.bounter.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BounterJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(BounterJobApplication.class, args);
    }
}
