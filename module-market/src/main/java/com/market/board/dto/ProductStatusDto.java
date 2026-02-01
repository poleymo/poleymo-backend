package com.market.board.dto;

import com.market.board.entity.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ProductStatusDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Request {
        private ProductStatus productStatus;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private ProductStatus productStatus;
        private String description;
    }

    public static ProductStatusDto.Response from(ProductStatus productStatus) {
        return ProductStatusDto.Response.builder()
                .productStatus(productStatus)
                .description(productStatus.getDescription())
                .build();
    }
}
