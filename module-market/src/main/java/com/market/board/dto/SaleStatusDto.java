package com.market.board.dto;

import com.market.board.entity.SaleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class SaleStatusDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Request {
        private SaleStatus saleStatus;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private SaleStatus saleStatus; // ENUM name (ON_SALE, RESERVED, ...)
        private String description; // Description (거래 대기, 거래 중, ...)
    }

    public static SaleStatusDto.Response from(SaleStatus saleStatus) {
        return SaleStatusDto.Response.builder()
                .saleStatus(saleStatus)
                .description(saleStatus.getDescription())
                .build();
    }
}