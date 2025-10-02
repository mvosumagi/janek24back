package ee.janek24back.service;

import ee.janek24back.controller.servicecategory.dto.ServiceCategoryDto;
import ee.janek24back.persistence.servicecategory.ServiceCategory;
import ee.janek24back.persistence.servicecategory.ServiceCategoryMapper;
import ee.janek24back.persistence.servicecategory.ServiceCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceCategoryService {
    private final ServiceCategoryRepository serviceCategoryRepository;
    private final ServiceCategoryMapper serviceCategoryMapper;

    public List<ServiceCategoryDto> findServiceCategories() {
        List<ServiceCategory> serviceCategories = serviceCategoryRepository.findAll();
        return serviceCategoryMapper.toServiceCategoryDtos(serviceCategories);
    }
}