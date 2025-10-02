package ee.janek24back.service;

import ee.janek24back.controller.providerservice.ProviderServiceInfo;
import ee.janek24back.controller.providerservice.dto.ProviderServiceDto;
import ee.janek24back.persistence.providerservice.ProviderService;
import ee.janek24back.persistence.providerservice.ProviderServiceMapper;
import ee.janek24back.persistence.providerservice.ProviderServiceRepository;
import ee.janek24back.persistence.providerservice.image.ProviderServiceImage;
import ee.janek24back.persistence.providerservice.image.ProviderServiceImageRepository;
import ee.janek24back.persistence.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderServiceService {

    private final ProviderServiceMapper providerServiceMapper;
    private final ProviderServiceRepository providerServiceRepository;
    private final ProviderServiceImageRepository imageRepository;

//    @Transactional(readOnly = true)
//    public byte[] getProviderServiceImage(Integer providerServiceId) {
//        return imageRepository.findByProviderServiceId(providerServiceId)
//                .map(ProviderServiceImage::getImageData)
//                .orElse(null);
//    }


    @Transactional(readOnly = true)
    public ProviderServiceDto getProviderServiceById(Integer providerServiceId) {
        ProviderService providerService = providerServiceRepository.findById(providerServiceId)
                .orElseThrow(() -> new RuntimeException("Service not found with id: " + providerServiceId));
        return providerServiceMapper.toProviderServiceDto(providerService);
    }


    public void updateProviderService(Integer providerServiceId, ProviderServiceDto providerServiceDto) {
        ProviderService existingService = providerServiceRepository.findById(providerServiceId)
                .orElseThrow(() -> new RuntimeException("Service not found with id: " + providerServiceId));

        ProviderService updatedService = providerServiceMapper.toProviderService(providerServiceDto);
        updatedService.setId(existingService.getId());
        updatedService.setUser(existingService.getUser());

        providerServiceRepository.save(updatedService);
    }


    @Transactional
    public void addProviderService(Integer userId, ProviderServiceDto providerServiceDto) {
        ProviderService providerService = createProviderService(userId, providerServiceDto);
        providerServiceRepository.save(providerService);

    }

    private ProviderService createProviderService(Integer userId, ProviderServiceDto providerServiceDto) {
        ProviderService providerService = providerServiceMapper.toProviderService(providerServiceDto);
        User user = new User();
        user.setId(userId);
        providerService.setUser(user);
        return providerService;
    }

    public List<ProviderServiceDto> getUserProviderServices(Integer userId) {
        List<ProviderService> providerServices = providerServiceRepository.findBy(userId);
        return providerServices.stream()
                .map(providerService -> {
                    ProviderServiceDto dto = providerServiceMapper.toProviderServiceDto(providerService);
                    return dto;
                })
                .toList();
    }

    public ProviderServiceInfo getServiceById(Integer serviceId) {
        ProviderService service = providerServiceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        return providerServiceMapper.toServiceInfo(service);
    }

    public List<ProviderServiceInfo> searchServices(String partialDescription) {
        List<ProviderService> providerServices = providerServiceRepository.findProviderServicesBy(partialDescription, partialDescription);
        return providerServiceMapper.toServiceInfos(providerServices);
    }

}