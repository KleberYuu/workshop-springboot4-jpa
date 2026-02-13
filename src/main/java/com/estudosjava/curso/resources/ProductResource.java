package com.estudosjava.curso.resources;


import com.estudosjava.curso.dto.product.ProductDetailsDTO;
import com.estudosjava.curso.dto.product.ProductRequestDTO;
import com.estudosjava.curso.dto.product.ProductResponseDTO;
import com.estudosjava.curso.entities.Product;
import com.estudosjava.curso.resources.exceptions.StandardError;
import com.estudosjava.curso.services.ProductService;
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
@RequestMapping(value = "/products")
@Tag(name = "Product Management", description = "APIs for managing products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @Operation(summary = "Get all products", description = "Retrieve a list all products in the system")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Products retrieved successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        List<ProductResponseDTO> list = service.findAll()
                .stream()
                .map(ProductResponseDTO::new)
                .toList();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Get products by ID", description = "Retrieve a product's details using their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product found successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDetailsDTO> findById(@PathVariable Long id){
        Product product = service.findById(id);
        return ResponseEntity.ok().body(new ProductDetailsDTO(product));
    }

    @Operation(summary = "Create a new product", description = "Add a new product to the system")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input - validation errors or business rule violation",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Product already exists",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @PostMapping
    public ResponseEntity<ProductDetailsDTO> insert(@RequestBody @Valid ProductRequestDTO dto) {
        Product product = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new ProductDetailsDTO(product));
    }

    @Operation(summary = "Update a product", description = "Update an existing product's details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product update successfully"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input - validation errors or business rule violation",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Product already exists",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            )
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDetailsDTO> update(@PathVariable Long id, @RequestBody ProductRequestDTO dto){
        Product product = service.update(id, dto);
        return ResponseEntity.ok().body(new ProductDetailsDTO(product));
    }

    @Operation(summary = "Delete a product", description = "Delete a product form system using their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found",
                    content = @Content(schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Integrity violation - Product cannot be deleted",
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
