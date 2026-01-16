package com.market.board.service;

import com.market.board.entity.ProductStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductStatusService {

    public ProductStatus getProductStatus(String productStatus) {
        return ProductStatus.valueOf(productStatus);
    }

    public List<ProductStatus> findAll() {
        return Arrays.asList(ProductStatus.values());
    }
}
