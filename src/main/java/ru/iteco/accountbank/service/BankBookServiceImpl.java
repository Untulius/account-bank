package ru.iteco.accountbank.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.iteco.accountbank.model.BankBookDto;
import ru.iteco.accountbank.model.exception.BankBookExistCurrencyException;
import ru.iteco.accountbank.model.exception.BankBookNotFoundException;
import ru.iteco.accountbank.model.exception.BankBookNumberUpdateException;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
@Slf4j
public class BankBookServiceImpl implements BankBookService {

    private final Map<Integer, BankBookDto> bankBookDtoMap = new ConcurrentHashMap<>();
    private final AtomicInteger sequenceId = new AtomicInteger(1);

    @PostConstruct
    void init() {
        bankBookDtoMap.put(1, BankBookDto.builder()
                .id(1)
                .userId(1)
                .amount(BigDecimal.valueOf(100l))
                .number("book1")
                .currency("GBP")
                .build()
        );
    }


    @Override
    public List<BankBookDto> getBankBookByUserId(Integer userId) {
        List<BankBookDto> bankBookDtoList = bankBookDtoMap.values().stream()
                .filter(bankBookDto -> userId.equals(bankBookDto.getUserId()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(bankBookDtoList)) {
            throw new BankBookNotFoundException("У клиента нет счетов");
        }
        return bankBookDtoList;
    }

    @Override
    public BankBookDto getBankBookById(Integer bankBookId) {
        BankBookDto bankBookDto = bankBookDtoMap.get(bankBookId);
        if (bankBookDto == null) {
            throw new BankBookNotFoundException("Счёт не найден");
        }
        return bankBookDto;
    }

    @Override
    public BankBookDto createBankBook(BankBookDto bankBookDto) {
        boolean hasBankBook = bankBookDtoMap.values().stream()
                .anyMatch(bankBook -> bankBook.getUserId().equals(bankBookDto.getUserId())
                        && bankBook.getNumber().equals(bankBookDto.getNumber())
                        && bankBook.getCurrency().equals(bankBookDto.getCurrency()));
        if (hasBankBook) {
            throw new BankBookExistCurrencyException("Счет с данной валютой уже имеется в хранилище:" + bankBookDto.getId());
        }
        int id = sequenceId.getAndIncrement();
        bankBookDto.setId(id);
        bankBookDtoMap.put(id, bankBookDto);
        return bankBookDto;
    }

    @Override
    public BankBookDto updateBankBook(BankBookDto bankBookDto) {
        BankBookDto bankBookDtoFromMap = bankBookDtoMap.get(bankBookDto.getId());
        if (bankBookDtoFromMap == null) {
            throw new BankBookNotFoundException("Лицевой счет не найден!");
        }
        if (!bankBookDtoFromMap.getNumber().equals(bankBookDto.getNumber())) {
            throw new BankBookNumberUpdateException("Номер счета менять нельзя!");
        }
        bankBookDtoMap.put(bankBookDto.getId(), bankBookDto);
        return bankBookDto;
    }

    @Override
    public void deleteBankBookById(Integer bankBookId) {
        bankBookDtoMap.remove(bankBookId);
    }

    @Override
    public void deleteBankBookByUserId(Integer userId) {
        List<Integer> bankBookRemoveId = bankBookDtoMap.values().stream()
                .filter(bankBookDto -> bankBookDto.getUserId().equals(userId))
                .map(BankBookDto::getId)
                .collect(Collectors.toList());

        for (Integer removeId : bankBookRemoveId) {
            bankBookDtoMap.remove(removeId);
        }
    }
}
