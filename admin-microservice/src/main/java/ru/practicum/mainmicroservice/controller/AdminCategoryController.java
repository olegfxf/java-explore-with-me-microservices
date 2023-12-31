package ru.practicum.mainmicroservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainmicroservice.dto.category.CategoryDto;
import ru.practicum.mainmicroservice.dto.category.NewCategoryDto;
import ru.practicum.mainmicroservice.messages.LogMessages;
import ru.practicum.mainmicroservice.service.AdminCategoryService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/admin/categories")
@Validated
public class AdminCategoryController {
    private final AdminCategoryService adminCategoryService;

    public AdminCategoryController(AdminCategoryService adminCategoryService) {
        this.adminCategoryService = adminCategoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto saveCategory(@Valid @RequestBody NewCategoryDto newCategoryDto) {
        log.debug(String.valueOf(LogMessages.TRY_ADD), "КАТЕГОРИЯ");
        return adminCategoryService.saveCategory(newCategoryDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        log.debug(String.valueOf(LogMessages.TRY_REMOVE_OBJECT), "КАТЕГОРИЯ");
        adminCategoryService.deleteCategory(id);
    }

    @PatchMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable Long id,
                                      @Valid @RequestBody NewCategoryDto newCategoryDto) {
        log.debug(String.valueOf(LogMessages.TRY_UPDATE), "КАТЕГОРИЯ");
        return adminCategoryService.updateCategory(id, newCategoryDto);
    }

}
