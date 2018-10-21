package com.iafnstudios;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Getter
@Component
public class GameImpl implements Game {




    @Getter(AccessLevel.NONE)
    private final NumberGenerator numberGenerator;
    private final int guessCount;

    private int number;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    @Setter
    private int guess;

    @Autowired
    public GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }


    /*
    //Constructor based dependency injection -- i set up in bean.xml with help of <constructor-arg>
    public GameImpl(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }*/

    @PostConstruct
    @Override
    public void reset() {
        guess=numberGenerator.getMinNumber();
        smallest = numberGenerator.getMinNumber();
        remainingGuesses = guessCount;
        biggest=numberGenerator.getMaxNumber();
        number=numberGenerator.next();
        log.debug("the number is {}",number);
    }

    @PreDestroy
    public void preDestroy(){
        log.info("In Game PreDestroy");
    }


    //Setter method based dependency injection -- i set up in bean.xml with help of <property>
    /*public void setNumberGenerator(NumberGenerator numberGenerator){
        this.numberGenerator = numberGenerator;
      }*/


    @Override
    public void check() {
        checkValidNumberRange();

        if (validNumberRange){
            if(guess>number){
                biggest = guess -1;
            }

            if (guess<number){
                smallest = guess + 1;
            }
        }

        remainingGuesses --;
    }

    @Override
    public boolean isGameWon() {
        return number == guess;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    private void checkValidNumberRange(){
        validNumberRange = (guess>=smallest) && (guess<=biggest);
    }
}
