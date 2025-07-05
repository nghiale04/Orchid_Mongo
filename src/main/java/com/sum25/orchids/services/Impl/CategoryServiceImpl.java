package com.sum25.orchids.services.Impl;

import com.sum25.orchids.dto.CategoryDto;
import com.sum25.orchids.entity.Categories;
import com.sum25.orchids.repository.CategoryrRepository;
import com.sum25.orchids.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryrRepository categoryrRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Categories> categories = categoryrRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Categories category : categories) {
            CategoryDto categoryDto = CategoryDto.builder()
                    .id(category.getId().toString())
                    .categoryName(category.getCategoryName())
                    .build();
            categoryDtos.add(categoryDto);
        }
        return  categoryDtos;
    }
}
