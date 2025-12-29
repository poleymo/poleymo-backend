package com.market.board.entity;

import com.market.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MarketBoard extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mbSeq; // 중고나라 게시글 키 (market_board_sequence)


    // 게시글 작성자 키, 게시글 상태 키, 게시된 물품 상태 키는 각각
    // User, BoardState, ProductState로 class를 생성하고, foreign key 설정을 해야한다.

    private int userSeq; // 중고나라 게시글 작성자 키

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbs_seq")
    private MarketBoardState marketBoardState; // 중고나라 게시글 상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ps_seq")
    private ProductState productState; // 중고나라 게시글 물품 상태

    private String title; // 중고나라 게시글 제목
    private int price; // 물품 가격

    @Column(name = "view_count") // mysql에서는 view가 이미 등록되어있으므로 view_count 사용
    private int view; // 중고나라 게시글 조회 수

    @Column(name = "like_count") // mysql에서는 like가 이미 등록되어있으므로 like_count 사용
    private int like; // 중고나라 게시글 좋아요 횟수
    private boolean activated; // 중고나라 게시글 활성화 여부
    private int reported; // 중고나라 게시글 신고 횟수
//    private String prdTag; // 상품 태그 (일단 구현 x) (테이블 or 컬럼, 적용 방식 논의 필요)

    private Boolean visible; // 중고나라 게시글 조회 가능 여부
}
