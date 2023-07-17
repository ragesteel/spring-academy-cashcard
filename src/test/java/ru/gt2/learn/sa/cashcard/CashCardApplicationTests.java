package ru.gt2.learn.sa.cashcard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

// Запускаем наше приложение так, чтобы оно было доступно для тестов
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CashCardApplicationTests {
	// Вспомогательного объект для тестирования приложения запущенного локально.
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void shouldReturnACashCardWhenDataIsSaved() {
		// Делаем запрос get к нашему приложению
		ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/99", String.class);
		// Убеждаемся что код ответа равер 200 OK
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void contextLoads() {
	}
}
