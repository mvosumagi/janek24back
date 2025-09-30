package ee.janek24back.controller.currency;

import ee.janek24back.controller.currency.dto.CurrencyDto;
import ee.janek24back.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping("/currencies")
    public List<CurrencyDto> findCurrencies() {
        return currencyService.findCurrencies();
    }
}