package ee.janek24back.service;

import ee.janek24back.controller.service.ServiceInfo;
import ee.janek24back.persistence.providerservice.ProviderService;
import ee.janek24back.persistence.providerservice.ProviderServiceMapper;
import ee.janek24back.persistence.providerservice.ProviderServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ProviderServiceRepository providerServiceRepository;
    private final ProviderServiceMapper providerServiceMapper;


    public List<ServiceInfo> searchServices(String partialDescription) {
        List<ProviderService> providerServices = providerServiceRepository.findProviderServicesBy(partialDescription, partialDescription);
        return providerServiceMapper.toServiceInfos(providerServices);
    }
}
