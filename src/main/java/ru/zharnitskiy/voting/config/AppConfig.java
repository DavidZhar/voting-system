package ru.zharnitskiy.voting.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("ru.zharnitskiy.voting")
@ImportResource("classpath:spring-security.xml")
@PropertySource("classpath:application.yml")
public class AppConfig {
}

