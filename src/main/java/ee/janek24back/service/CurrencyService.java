package ee.janek24back.service;

import ee.janek24back.controller.currency.dto.CurrencyDto;
import ee.janek24back.persistence.currency.Currency;
import ee.janek24back.persistence.currency.CurrencyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final ee.janek24back.persistence.currency.CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;

    public List<CurrencyDto> findCurrencies() {
        List<Currency> currencies = currencyRepository.findAll();
        return currencyMapper.toCurrencyDtos(currencies);
    }
}