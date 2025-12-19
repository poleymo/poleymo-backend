package com.market.board.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MarketBoardDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Request {
        private int userSeq; // 중고나라 게시글 작성자 키
        private int mbsSeq; // 중고나라 게시글 상태 키 (board_state_seq)
        private int psSeq; // 게시된 물품 상태 키 (product_state_seq)
        private String title; // 중고나라 게시글 제목
        private int price; // 물품 가격
        private int view; // 중고나라 게시글 조회 수
        private int like; // 중고나라 게시글 좋아요 횟수
        private boolean activated; // 중고나라 게시글 활성화 여부
        private int reported; // 중고나라 게시글 신고 횟수
//        private String prdTag; // 상품 태그 (일단 구현 x) (테이블 or 컬럼, 적용 방식 논의 필요)
    }

    /*
    게시글 작성자 키, 게시글 상태 키, 게시된 물품 상태 키는 각각
    User, BoardState, ProductState로 class를 생성하고, foreign key 설정을 해야한다.
     */

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private int mbSeq; // 중고나라 게시글 키 (marketBoardSequence)
        private int userSeq; // 중고나라 게시글 작성자 키
        private int mbsSeq; // 중고나라 게시글 상태 키 (board_state_seq)
        private int psSeq; // 게시된 물품 상태 키 (product_state_seq)
        private String title; // 중고나라 게시글 제목
        private int price; // 물품 가격
        private int view; // 중고나라 게시글 조회 수
        private int like; // 중고나라 게시글 좋아요 횟수
        private boolean activated; // 중고나라 게시글 활성화 여부
        private int reported; // 중고나라 게시글 신고 횟수
//        private String prdTag; // 상품 태그 (일단 구현 x) (테이블 or 컬럼, 적용 방식 논의 필요)
    }

}
