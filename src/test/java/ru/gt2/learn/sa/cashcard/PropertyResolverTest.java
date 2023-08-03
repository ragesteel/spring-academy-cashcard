package ru.gt2.learn.sa.cashcard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("unit")
public class PropertyResolverTest {

    @Autowired
    private PropertyResolver propertyResolver;

    @Test
    public void shouldTestResourceFile_overridePropertyValues() {
        String firstProperty = propertyResolver.getFirst();
        String secondProperty = propertyResolver.getSecond();

        assertEquals("main", firstProperty);
        assertEquals("test", secondProperty);
    }
}