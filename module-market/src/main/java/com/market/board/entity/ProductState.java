package com.market.board.entity;

import com.market.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProductState extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int psSeq; // 물품 상태 키 (product_state_seq)

    private String prdState; // 물품 상태 정보 (product_state) (새 상품, 단순 개봉, 최상급, 상급, 중급, 하급 등)
    private boolean visible; // 물품 상태 정보 조회 가능 여부
}
