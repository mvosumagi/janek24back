package ee.janek24back.service;

import ee.janek24back.controller.service.ServiceInfo;
import ee.janek24back.persistence.providerservice.ProviderService;
import ee.janek24back.persistence.providerservice.ProviderServiceMapper;
import ee.janek24back.persistence.providerservice.ProviderServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ProviderServiceRepository providerServiceRepository;
    private final ProviderServiceMapper providerServiceMapper;


    public List<ServiceInfo> searchServices(String partialDescription) {
        // todo: küsi kõik read category_image tabelist
        // todo: tee meetod mis paneks kokku hasmap objekti intetger (categoriId) String (category bilt STRINGINA)

        HashMap<Integer, String> categoryImageMap = new HashMap<>();
        categoryImageMap.put(1, "AADSDASDASDASDSAD");
        categoryImageMap.put(2, "ASDSADASDASDASD");


        List<ProviderService> providerServices = providerServiceRepository.findProviderServicesBy(partialDescription, partialDescription);
        List<ServiceInfo> serviceInfos = providerServiceMapper.toServiceInfos(providerServices);
        // tof

        for (ServiceInfo serviceInfo : serviceInfos) {
            // todo: Kui service_image tabelis leidub dto 'serviceId' väärtuse abil pilt,
            // siis kasutada serviceInfo objektis seda pilti
            // kui service service_image tabelis ei ole pilit siis kasutada category mapist olevat pilti
            serviceInfo.setImageData(categoryImageMap.get(serviceInfo.getCategoryId()));

        }
        return serviceInfos;
    }
}
