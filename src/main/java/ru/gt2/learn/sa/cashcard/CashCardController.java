package ru.gt2.learn.sa.cashcard;

import java.util.Optional;
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
    private final CashCardRepository cashCardRepository;

    public CashCardController(CashCardRepository cashCardRepository) {
        this.cashCardRepository = cashCardRepository;
    }

    @GetMapping("/{requestedId}")
    public ResponseEntity<CashCard> findById(
            // передача параметра из пути в метод контроллера
            @PathVariable Long requestedId) {
        Optional<CashCard> optionalCashCard = cashCardRepository.findById(requestedId);
        if (optionalCashCard.isPresent()) {
            return ResponseEntity.ok(optionalCashCard.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
