package ee.janek24back.service;

import ee.janek24back.controller.teenus.dto.TeenusDto;
import ee.janek24back.persistence.providerservice.ProviderServiceImage;
import ee.janek24back.persistence.providerservice.ProviderServiceImageRepository;
import ee.janek24back.persistence.teenus.Teenus;
import ee.janek24back.persistence.teenus.TeenusMapper;
import ee.janek24back.persistence.teenus.TeenusRepository;
import ee.janek24back.persistence.user.User;
import ee.janek24back.persistence.util.BytesConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeenusService {

    private final TeenusMapper teenusMapper;
    private final TeenusRepository teenusRepository;
    private final ProviderServiceImageRepository imageRepository;

    @Transactional
    public void addTeenus(Integer userId, TeenusDto teenusDto) {
        Teenus teenus = createTeenus(userId, teenusDto);
        teenusRepository.save(teenus);

        if (teenusDto.getImageBase64() != null && !teenusDto.getImageBase64().isEmpty()) {
            String base64Data = teenusDto.getImageBase64();
            if (base64Data.contains(",")) {
                base64Data = base64Data.split(",")[1];
            }

            ProviderServiceImage image = new ProviderServiceImage();
            image.setTeenus(teenus);
            image.setImageData(BytesConverter.stringToBytes(base64Data));
            imageRepository.save(image);
        }
    }

    private Teenus createTeenus(Integer userId, TeenusDto teenusDto) {
        Teenus teenus = teenusMapper.toTeenus(teenusDto);
        User user = new User();
        user.setId(userId);
        teenus.setUser(user);
        return teenus;
    }

    public List<TeenusDto> getUserTeenused(Integer userId) {
        List<Teenus> teenused = teenusRepository.findBy(userId);
        return teenused.stream()
                .map(teenus -> {
                    TeenusDto dto = teenusMapper.toTeenusDto(teenus);

                    Optional<ProviderServiceImage> optionalImage = imageRepository.findByTeenusId(teenus.getId().longValue());
                    if (optionalImage.isPresent()) {
                        ProviderServiceImage image = optionalImage.get();
                        byte[] imageBytes = image.getImageData();
                        dto.setImageBase64("data:image/jpeg;base64," + BytesConverter.bytesToString(imageBytes));
                    }

                    return dto;
                })
                .toList();
    }
}