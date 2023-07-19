package ru.gt2.learn.sa.cashcard;

import org.springframework.data.annotation.Id;

public record CashCard(@Id Long id, Double amount) {
}
