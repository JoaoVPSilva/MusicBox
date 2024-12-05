package com.music.musicbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.music.musicbox.repository")
@EntityScan(basePackages = "com.music.musicbox.entity")
@ComponentScan
public class MusicBoxApplication {
    private static final Logger log = LoggerFactory.getLogger(MusicBoxApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MusicBoxApplication.class, args);
    }
}
