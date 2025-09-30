package ee.janek24back.persistence.teenus;

import ee.janek24back.controller.teenus.dto.TeenusDto;
import ee.janek24back.persistence.providerservice.ProviderServiceImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.Base64;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TeenusMapper {
    @Mapping(source = "currencyIsId", target = "currencyIs.id")
    @Mapping(source = "serviceCategoryId", target = "serviceCategory.id")
    Teenus toTeenus(TeenusDto teenusDto);

    @Mapping(source = "currencyIs.id", target = "currencyIsId")
    @Mapping(source = "serviceCategory.id", target = "serviceCategoryId")
    TeenusDto toTeenusDto(Teenus teenus);

    default TeenusDto toDtoWithImage(Teenus teenus, ProviderServiceImage image) {
        TeenusDto dto = toTeenusDto(teenus);
        if (image != null && image.getImageData() != null) {
            String base64Image = Base64.getEncoder().encodeToString(image.getImageData());
            dto.setImageBase64("data:image/jpeg;base64," + base64Image);
        }
        return dto;
    }

    default ProviderServiceImage toImageEntity(String base64, Teenus teenus) {
        if (base64 == null || base64.isEmpty()) return null;

        ProviderServiceImage image = new ProviderServiceImage();
        image.setTeenus(teenus);

        String base64Data = base64;
        if (base64.contains(",")) {
            base64Data = base64.split(",")[1];
        }

        try {
            image.setImageData(Base64.getDecoder().decode(base64Data));
        } catch (IllegalArgumentException e) {
            return null;
        }

        return image;
    }
}