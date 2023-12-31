package ru.gt2.learn.sa.cashcard;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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

    @PostMapping
    private ResponseEntity createCashCard(
            // Тело запроса, Spring сам десириализует данные в класс CashCard
            @RequestBody CashCard newCashCardRequest,
            // Построитель URI инъектируется Spring'ом
            UriComponentsBuilder ucb) {
        // Сохранить объект и вернуть новый с заполненным идентификатором
        // А если передавать объект с заполненным идентификатором, то он попытается сделать UPDATE вместо INSERT!
        CashCard savedCashCard = cashCardRepository.save(newCashCardRequest);

        // Созждание URI для свежесозданной CashCard
        URI locationOfNewCashCard = ucb
                .path("cashcards/{id}")
                .buildAndExpand(savedCashCard.id())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    }

    @GetMapping
    public ResponseEntity<List<CashCard>> findAll(
            // Инъектируется Spring'ом из параметров запроса, по умолчанию page=0, size=20
            Pageable pageable) {
        Page<CashCard> page = cashCardRepository.findAll(PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))
        ));
        return ResponseEntity.ok(page.getContent());
    }
}
