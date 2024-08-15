package com.example.adapters.inbound.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private Long id;
    private String nameProduct;
    private String brandProduct;
    private String descriptionProduct;
    private Float priceProduct;
    private String imageProduct;
    private Integer amount;
}
