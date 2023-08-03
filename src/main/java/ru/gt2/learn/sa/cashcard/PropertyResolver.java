package ru.gt2.learn.sa.cashcard;

// Based on https://www.baeldung.com/spring-tests-override-properties

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertyResolver {

    @Value("${rage.first}")
    private String first;

    @Value("${rage.second}")
    private String second;

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }
}