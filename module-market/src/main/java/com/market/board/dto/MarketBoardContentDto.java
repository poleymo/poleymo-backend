package com.market.board.dto;

import com.market.board.entity.MarketBoardContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MarketBoardContentDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Request {
        private Long mbSeq; // 중고나라 게시글 키 (market_board_sequence)
        private String content; // 중고나라 게시글 내용
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private Long mbcSeq; // 중고나라 게시글 내용 키 (market_board_content_sequence)
        private MarketBoardDto.Response marketBoard; // 중고나라 게시글 키 (market_board_sequence)
        private String content; // 중고나라 게시글 내용
        private Boolean visible; // 중고나라 게시글 내용 조회 가능 여부
    }

    public static MarketBoardContentDto.Response from(MarketBoardContent marketBoardContent) {
        return Response.builder()
                .mbcSeq(marketBoardContent.getMbcSeq())
                .marketBoard(MarketBoardDto.from(marketBoardContent.getMarketBoard()))
                .content(marketBoardContent.getContent())
                .visible(marketBoardContent.getVisible())
                .build();
    }
}
