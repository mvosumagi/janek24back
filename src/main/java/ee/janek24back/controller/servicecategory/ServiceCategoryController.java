package ee.janek24back.controller.servicecategory;


import ee.janek24back.controller.servicecategory.dto.ServiceCategoryDto;
import ee.janek24back.service.ServiceCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ServiceCategoryController {

    private final ServiceCategoryService serviceCategoryService;

    @GetMapping("/service-categories")
    public List<ServiceCategoryDto> findServiceCategories() {
        return serviceCategoryService.findServiceCategories();
    }


}


