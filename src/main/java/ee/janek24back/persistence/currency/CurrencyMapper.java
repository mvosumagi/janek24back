package ee.janek24back.persistence.currency;

import ee.janek24back.controller.currency.dto.CurrencyDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CurrencyMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "description", target = "description")
    CurrencyDto toCurrencyDto(Currency currency);

    List<CurrencyDto> toCurrencyDtos(List<Currency> currencies);
}