package com.market.board.dto;

import com.market.board.entity.SaleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class SaleStatusDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Request {
        private String mbState; // 중고나라 게시글 상태 (market_board_state) (거래 대기, 거래 중, 거래 종료)
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private Long ssSeq; // 중고나라 게시글 상태 키 (sale_status_sequence)
        private String mbState; // 중고나라 게시글 상태 (market_board_state) (거래 대기, 거래 중, 거래 종료)
        private Boolean visible; // 중고나라 게시글 상태 조회 가능 여부
    }

    public static SaleStatusDto.Response from(SaleStatus saleStatus) {
        return SaleStatusDto.Response.builder()
                .ssSeq(saleStatus.getSsSeq())
                .mbState(saleStatus.getMbState())
                .visible(saleStatus.getVisible())
                .build();
    }
}
