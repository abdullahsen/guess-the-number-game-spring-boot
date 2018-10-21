package com.iafnstudios.config;

import com.iafnstudios.GuessCount;
import com.iafnstudios.MaxNumber;
import com.iafnstudios.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.iafnstudios")
@PropertySource("classpath:config/game.properties")
public class GameConfig {

    @Value("${game.minNumber}")
    private int minNumber;

    @Value("${game.maxNumber}")
    private int maxNumber;

    @Value("${game.guessCount}")
    private int guessCount;

    @Bean
    @MaxNumber
    public int maxNumber(){
        return maxNumber;
    }

    @Bean
    @MinNumber
    public int minNumber(){
        return minNumber;
    }

    @Bean
    @GuessCount
    public int guessCount(){
        return guessCount;
    }
}
