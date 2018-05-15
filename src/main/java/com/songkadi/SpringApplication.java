package com.songkadi;

import com.songkadi.configuration.SpringConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(SpringConfiguration.class)
@SpringBootApplication(scanBasePackages = {"com.songkadi"})
// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class SpringApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }
}
