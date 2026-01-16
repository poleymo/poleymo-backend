package com.market.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SaleStatus {

    ON_SALE("거래 대기"),
    RESERVED("거래 중"),
    SOLD_OUT("거래 종료");

    private final String description;
}