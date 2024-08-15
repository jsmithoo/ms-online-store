package com.example.application.port.in;

import com.example.adapters.outbound.repository.entities.Product;

public interface SaveProductQuery {

    Product execute(Product product);
}
