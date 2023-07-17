package ru.gt2.learn.sa.cashcard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Скажет Spring'у что этот класс — это компонент типа RestController, который может обрабатывать HTTP-запросы
@RestController
// Указываем какие адреса мы будем обрабатывать
@RequestMapping("/cashcards")
public class CashCardController {
    @GetMapping("/{requestedId}")
    public ResponseEntity<CashCard> findById(
            // передача параметра из пути в метод контроллера
            @PathVariable Long requestedId) {
        if (requestedId.equals(99L)) {
            CashCard cashCard = new CashCard(99L, 123.45);
            return ResponseEntity.ok(cashCard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
