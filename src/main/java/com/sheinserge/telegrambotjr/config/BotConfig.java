package com.sheinserge.telegrambotjr.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

@Configuration
@PropertySource("application.properties")
@Data
public class BotConfig {
    @Value("$bot.name")
    public String name;
    @Value("$bot.key")
    public String token;
}
