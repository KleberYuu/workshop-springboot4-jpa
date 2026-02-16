package com.estudosjava.curso.resources;


import com.estudosjava.curso.dto.category.CategoryDetailsDTO;
import com.estudosjava.curso.dto.category.CategoryRequestDTO;
import com.estudosjava.curso.dto.category.CategoryResponseDTO;
import com.estudosjava.curso.entities.Category;
import com.estudosjava.curso.resources.exceptions.StandardError;
import com.estudosjava.curso.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
@Tag(name = "Category Management", description = "APIs for managing categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @Operation(summary = "Get all categories", description = "Retrieve a list all categories in the system")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categories retrieved successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        List<CategoryResponseDTO> list = service.findAll()
                .stream()
                .map(CategoryResponseDTO::new)
                .toList();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Get categories by ID", description = "Retrieve a category's details using their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category found successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Category not found",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDetailsDTO> findById(@PathVariable Long id){
        Category category = service.findById(id);
        return ResponseEntity.ok().body(new CategoryDetailsDTO(category));
    }

    @Operation(summary = "Create a new category", description = "Add a new category to the system")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input - validation errors or business rule violation",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Category already exists",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @PostMapping
    public ResponseEntity<CategoryDetailsDTO> insert(@RequestBody @Valid CategoryRequestDTO dto){
        Category category = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new CategoryDetailsDTO(category));
    }

    @Operation(summary = "Update a category", description = "Update an existing category's details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category update successfully"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input - validation errors or business rule violation",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Category not found",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Category already exists",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDetailsDTO> update(@PathVariable Long id, @RequestBody @Valid CategoryRequestDTO dto){
        Category category = service.update(id, dto);
        return ResponseEntity.ok().body(new CategoryDetailsDTO(category));
    }

    @Operation(summary = "Delete a category", description = "Delete a category form system using their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Category not found",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Integrity violation - Category cannot be deleted",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
