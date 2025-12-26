package com.market.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MarketBoardContentDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Request {
        private int mbSeq; // 중고나라 게시글 키 (market_board_sequence)
        private String content; // 중고나라 게시글 내용
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private int mbcSeq; // 중고나라 게시글 내용 키 (market_board_content_sequence)
        private int mbSeq; // 중고나라 게시글 키 (market_board_sequence)
        private String content; // 중고나라 게시글 내용
        private Boolean visible; // 중고나라 게시글 내용 조회 가능 여부
    }
}
