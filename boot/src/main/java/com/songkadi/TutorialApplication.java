package com.songkadi;

import com.songkadi.configuration.TutorialConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(TutorialConfiguration.class)
@SpringBootApplication(scanBasePackages = {"com.songkadi"})
public class TutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorialApplication.class, args);
    }
}
