package com.example.application.usecases;

import com.example.adapters.outbound.repository.entities.Product;
import com.example.application.port.in.FindByNameProductQuery;
import com.example.application.port.out.ProductRepository;
import com.example.domain.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindByNameProduct implements FindByNameProductQuery {

    private final ProductRepository productRepository;

    @Override
    public Product execute(String nameProduct) {
        return productRepository
                .findByNameProduct(nameProduct)
                .orElseThrow(() -> new NotFoundException(Product.class.getSimpleName(), nameProduct));
    }
}
