package com.shop.product;

import com.shop.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProductForm {

    @NotBlank
    private String name;

    @Positive
    private int price;

    @Positive
    @Min(value = 1)
    private int productCnt;

    private LocalDateTime startDate;


    private String productImage;

    @NotBlank
    private String description;

    private Long path;

    public ProductForm(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.startDate = product.getStartDate();
        this.productCnt = product.getProductCnt();
        this.productImage = product.getProductImage();
        this.description = product.getDescription();
    }

}
