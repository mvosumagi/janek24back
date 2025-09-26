package ee.janek24back.service;

import ee.janek24back.controller.service.ServiceInfo;
import ee.janek24back.persistence.service.ServiceMapper;
import ee.janek24back.persistence.service.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public ServiceInfo findService() {
        return null;
    }

//    public ServiceInfo findService(Integer serviceId) {
//        ServiceInfo serviceInfo = getValidServiceInfo(serviceId);
//        return serviceInfo;
//    }

//    private ServiceInfo getValidServiceInfo(Integer serviceId) {
//    }

//    public List<ServiceInfo> findServices() {
//        List<ee.janek24back.persistence.service.Service> services = serviceRepository.findAll();
//        return serviceMapper.toServiceInfos(services);
//    }

}
