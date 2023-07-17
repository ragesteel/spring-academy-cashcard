package ru.gt2.learn.sa.cashcard;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

// JsonTest отметим тестовый, как класс используеющий Jackson
@JsonTest
public class CashCardJsonTest {
    @Autowired
    // JacksonTest — обёртка для облегчения тестирования!
    private JacksonTester<CashCard> json;

    @Test
    public void cashCardSerializationTest() throws IOException {
        CashCard cashCard = new CashCard(99L, 123.45);

        assertThat(json.write(cashCard)).isStrictlyEqualToJson("expected.json");

        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");

        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);

        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");

        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount")
                .isEqualTo(123.45);
    }

    @Test
    public void myFirstTest() {
        assertThat(42).isEqualTo(42);
    }
}
