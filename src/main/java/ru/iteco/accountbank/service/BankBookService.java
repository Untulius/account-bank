package ru.iteco.accountbank.service;

import ru.iteco.accountbank.model.BankBookDto;

import java.util.List;

public interface BankBookService {

    List<BankBookDto> getBankBookByUserId(Integer userId);

    BankBookDto getBankBookById(Integer bankBookId);

    BankBookDto createBankBook(BankBookDto bankBookDto);

    BankBookDto updateBankBook(BankBookDto bankBookDto);

    void deleteBankBookById(Integer bankBookId);

    void deleteBankBookByUserId(Integer userId);

}
