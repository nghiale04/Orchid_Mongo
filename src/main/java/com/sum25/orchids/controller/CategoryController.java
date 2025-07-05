package com.sum25.orchids.controller;

import com.sum25.orchids.dto.CategoryDto;
import com.sum25.orchids.dto.ResponseDTO;
import com.sum25.orchids.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllCategories() {
        List<CategoryDto> categoryDtos = categoryService.getAllCategories();
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .statusCode("200")
                        .message("Categories fetched successfully")
                        .data(categoryDtos)
                        .build()
        );
    }
}
