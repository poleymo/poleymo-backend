package com.market.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MarketBoardStateDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Request {
        private String mbState; // 중고나라 게시글 상태 (market_board_state)
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private int mbsSeq; // 중고나라 게시글 상태 키 (market_board_state_sequence)
        private String mbState; // 중고나라 게시글 상태 (market_board_state)
    }
}
