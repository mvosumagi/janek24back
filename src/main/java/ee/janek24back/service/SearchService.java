package ee.janek24back.service;

import ee.janek24back.controller.providerservice.ProviderServiceInfo;
import ee.janek24back.persistence.providerservice.ProviderService;
import ee.janek24back.persistence.providerservice.ProviderServiceMapper;
import ee.janek24back.persistence.providerservice.ProviderServiceRepository;
import ee.janek24back.persistence.providerservice.image.ProviderServiceImage;
import ee.janek24back.persistence.providerservice.image.ProviderServiceImageRepository;
import ee.janek24back.persistence.servicecategory.ServiceCategoryImage;
import ee.janek24back.persistence.servicecategory.ServiceCategoryImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ProviderServiceRepository providerServiceRepository;
    private final ProviderServiceMapper providerServiceMapper;
    private final ProviderServiceImageRepository providerServiceImageRepository;
    private final ServiceCategoryImageRepository serviceCategoryImageRepository;

    public List<ProviderServiceInfo> search(String query) {
        List<ProviderService> providerServices =
                providerServiceRepository.findByDescriptionShortContainingIgnoreCaseOrDescriptionLongContainingIgnoreCase(query, query);

        List<ProviderServiceInfo> serviceInfos = providerServiceMapper.toServiceInfos(providerServices);

        Map<Integer, String> categoryImageMap = new HashMap<>();
        for (ServiceCategoryImage image : serviceCategoryImageRepository.findAll()) {
            categoryImageMap.put(image.getServiceCategoryId(), encode(image.getImageData()));
        }

        Map<Integer, String> serviceImageMap = new HashMap<>();
        for (ProviderServiceImage image : providerServiceImageRepository.findAll()) {
            serviceImageMap.put(image.getProviderServiceId(), encode(image.getImageData()));
        }

        for (ProviderServiceInfo service : serviceInfos) {
            Integer serviceId = service.getServiceId();
            Integer categoryId = service.getCategoryId();

            if (serviceImageMap.containsKey(serviceId)) {
                service.setImageData(serviceImageMap.get(serviceId));
            } else if (categoryImageMap.containsKey(categoryId)) {
                service.setImageData(categoryImageMap.get(categoryId));
            } else {
                service.setImageData(null); // or a default "no image" base64 string
            }
        }

        return serviceInfos;
    }

    private String encode(byte[] imageData) {
        return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageData);
    }
}
