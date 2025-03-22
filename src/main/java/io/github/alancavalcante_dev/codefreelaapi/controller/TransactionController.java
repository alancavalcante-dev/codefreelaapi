package io.github.alancavalcante_dev.codefreelaapi.controller;

import io.github.alancavalcante_dev.codefreelaapi.model.Transactions;
import io.github.alancavalcante_dev.codefreelaapi.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionsService transactionService;

    @PostMapping
    public ResponseEntity<Transactions> create(@RequestBody Transactions transaction) {
        return ResponseEntity.ok(transactionService.createTransaction(transaction));
    }
}
