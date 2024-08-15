package com.example.adapters.inbound.controller;

import com.example.adapters.inbound.controller.dto.ProductDto;
import com.example.adapters.inbound.controller.mapper.ProductMapper;
import com.example.adapters.outbound.repository.entities.Product;
import com.example.application.ProductService;
import com.example.application.paramas.ProductUpdateParams;
import com.example.application.port.in.DeleteByIdQuery;
import com.example.application.port.in.FindByNameProductQuery;
import com.example.application.port.in.FindProductByIdQuery;
import com.example.application.port.in.SaveProductQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*")
@RequestMapping(value = "/api/v1.0/products")
@RestController
@RequiredArgsConstructor
@Tag(name = "Products API")
public class ProductController {

    private final ProductService productService;

    private final FindProductByIdQuery findProductByIdQuery;

    private final FindByNameProductQuery findByNameProductQuery;

    private final SaveProductQuery saveProductQuery;

    private final DeleteByIdQuery deleteByIdQuery;

    @Operation(summary = "Get a product by id", description = "Returns a product as per the id")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
                @ApiResponse(responseCode = "404", description = "Not found - The product was not found"),
                @ApiResponse(responseCode = "401", description = "Unauthorization"),
                @ApiResponse(responseCode = "403", description = "Forbidden"),
                @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(findProductByIdQuery.execute(id));
    }

    @GetMapping("/name-product")
    public ResponseEntity<Product> findByNameProduct(@RequestParam String nameProduct) {
        return ResponseEntity.ok(findByNameProductQuery.execute(nameProduct));
    }

    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
        Product product = saveProductQuery.execute(ProductMapper.map(productDto));
        return new ResponseEntity<>(ProductMapper.map(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        deleteByIdQuery.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long id, @RequestBody ProductUpdateParams productUpdateParams) {
        Product product = productService.updateProduct(id, productUpdateParams);
        return ResponseEntity.ok(ProductMapper.map(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAllProducts(
            @RequestParam(required = false) String keyword, @RequestParam int offset, @RequestParam int limit) {
        List<Product> products = productService.findAllProducts(keyword, offset, limit);
        return ResponseEntity.ok(ProductMapper.map(products));
    }
}

// TODO Añadir Swagger, Utilizar JWT para autorización, Implementar autenticacion estudiar filtro http, test unitario
