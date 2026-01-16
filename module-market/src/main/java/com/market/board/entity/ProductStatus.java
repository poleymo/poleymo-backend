package com.market.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStatus {

    NEW("새 상품"),
    OPENED("단순 개봉"),
    BEST("최상급"),
    GOOD("상급"),
    MID("중급"),
    LOW("하급");

    private final String description;
}
