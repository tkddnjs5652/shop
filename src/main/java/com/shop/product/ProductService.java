package com.shop.product;

import com.shop.domain.OrdersProduct;
import com.shop.domain.Product;
import com.shop.ordersProduct.OrdersProductForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public Product saveNewProduct(ProductForm productForm) {
        Product product = Product.builder()
                .name(productForm.getName())
                .price(productForm.getPrice())
                .productCnt(productForm.getProductCnt())
                .productImage(productForm.getProductImage())
                .description(productForm.getDescription())
                .build();
        Product newProduct = productRepository.save(product);
        return newProduct;

}

    public void uploadData(Product product) {
        product.uploadDate();
        product.uploadPath();
    }


}
