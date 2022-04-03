package ru.iteco.accountbank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iteco.accountbank.model.BankBookDto;
import ru.iteco.accountbank.model.exception.BankBookNotFoundException;
import ru.iteco.accountbank.service.BankBookService;

import java.util.List;

@RestController
@RequestMapping("/bank-book")
public class BankBookController {

    private final BankBookService bankBookService;


    public BankBookController(BankBookService bankBookService) {
        this.bankBookService = bankBookService;
    }

    @GetMapping("/{bankBookId}")
    public ResponseEntity<BankBookDto> getBankBookById(@PathVariable Integer bankBookId) {
        return ResponseEntity.ok(bankBookService.getBankBookById(bankBookId));
    }

    @GetMapping("/")
    public void getBankBookWithoutId() {
        throw new BankBookNotFoundException("Счёт не найден");
    }

    @GetMapping("/by-user-id/{userId}")
    public ResponseEntity<List<BankBookDto>> getBankBookByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(bankBookService.getBankBookByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<BankBookDto> createBankBook(@RequestBody BankBookDto bankBookDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bankBookService.createBankBook(bankBookDto));
    }

    @PutMapping
    public BankBookDto updateBankBook(@RequestBody BankBookDto bankBookDto) {
        return bankBookService.updateBankBook(bankBookDto);
    }

    @DeleteMapping("/{bankBookId}")
    public void deleteBankBook(@PathVariable Integer bankBookId) {
        bankBookService.deleteBankBookById(bankBookId);
    }

    @DeleteMapping("/by-user-id/{userId}")
    public void deleteBankBookByUserId(@PathVariable Integer userId) {
        bankBookService.deleteBankBookByUserId(userId);
    }
}
