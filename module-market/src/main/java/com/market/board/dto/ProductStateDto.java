package com.market.board.dto;

import com.market.board.entity.ProductState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ProductStateDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Request {
        private String prdState; // 물품 상태 정보 (product_state) (새 상품, 단순 개봉, 최상급, 상급, 중급, 하급 등)
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private int psSeq; // 물품 상태 키 (product_state_seq)
        private String prdState; // 물품 상태 정보 (product_state) (새 상품, 단순 개봉, 최상급, 상급, 중급, 하급 등)
        private Boolean visible; // 물품 상태 정보 조회 가능 여부
    }

    public static ProductStateDto.Response from(ProductState productState) {
        return ProductStateDto.Response.builder()
                .psSeq(productState.getPsSeq())
                .prdState(productState.getPrdState())
                .visible(productState.getVisible())
                .build();
    }
}
