package ru.zharnitskiy.voting.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("ru.zharnitskiy.voting")
@ImportResource("classpath:spring-security.xml")
public class AppConfig {
}

