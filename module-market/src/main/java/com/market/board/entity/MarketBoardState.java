package com.market.board.entity;

import com.market.util.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MarketBoardState extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mbsSeq; // 중고나라 게시글 상태 키 (market_board_state_sequence)

    private String mbState; // 중고나라 게시글 상태 (market_board_state) (거래 대기, 거래 중, 거래 종료)
    private Boolean visible; // 중고나라 게시글 상태 조회 가능 여부
}
