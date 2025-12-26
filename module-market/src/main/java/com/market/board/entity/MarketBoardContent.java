package com.market.board.entity;

import com.market.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MarketBoardContent extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mbcSeq; // 중고나라 게시글 내용 키 (market_board_content_sequence)

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mb_seq")
    private MarketBoard marketBoard; // 중고나라 게시글 키 (market_board_sequence)

    private String content; // 중고나라 게시글 내용
    public Boolean visible; // 중고나라 게시글 내용 조회 가능 여부

    public int getMbSeq() {
        return marketBoard.getMbSeq();
    }
}
