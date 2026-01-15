package com.market.board.dto;

import com.market.board.entity.MarketBoard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MarketBoardDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Create {
        private Long userSeq; // 중고나라 게시글 작성자 키
        private Long ssSeq; // 중고나라 게시글 상태 키 (sale_status_seq)
        private Long psSeq; // 게시된 물품 상태 키 (product_state_seq)
        private String title; // 중고나라 게시글 제목
        private Long price; // 물품 가격
//        private String prdTag; // 상품 태그 (일단 구현 x) (테이블 or 컬럼, 적용 방식 논의 필요)
        private String pictureUrl; // 중고나라 게시글 사진url
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Update {
        private Long mbSeq; // 중고나라 게시글 키 (market_board_sequence)
        private Long userSeq; // 중고나라 게시글 작성자 키
        private Long ssSeq; // 중고나라 게시글 상태 키 (sale_status_seq)
        private Long psSeq; // 중고나라 게시글 물품 상태 (product_state_sequence)
        private String title; // 중고나라 게시글 제목
        private Long price; // 물품 가격
//        private String prdTag; // 상품 태그 (일단 구현 x) (테이블 or 컬럼, 적용 방식 논의 필요)
        private Boolean visible; // 중고나라 게시글 조회 가능 여부
        private String pictureUrl; // 중고나라 게시글 사진url
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Delete {
        private Long mbSeq; // 중고나라 게시글 키 (market_board_sequence)
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private Long mbSeq; // 중고나라 게시글 키 (market_board_sequence)
        private Long userSeq; // 중고나라 게시글 작성자 키
        private SaleStatusDto.Response saleStatus; // 중고나라 게시글 상태
        private ProductStateDto.Response productState; // 게시된 물품 상태 키
        private String title; // 중고나라 게시글 제목
        private Long price; // 물품 가격
        private Long views; // 중고나라 게시글 조회 수
        private Long likes; // 중고나라 게시글 좋아요 횟수
        private Long reports; // 중고나라 게시글 신고 횟수
//        private String prdTag; // 상품 태그 (일단 구현 x) (테이블 or 컬럼, 적용 방식 논의 필요)
        private Boolean visible; // 게시글 조회 가능 여부
        private String pictureUrl; // 중고나라 게시글 사진url
    }

    public static MarketBoardDto.Response from(MarketBoard marketBoard) {
        return Response.builder()
                .mbSeq(marketBoard.getMbSeq())
                .userSeq(marketBoard.getUserSeq())
                .saleStatus(SaleStatusDto.from(marketBoard.getSaleStatus()))
                .productState(ProductStateDto.from(marketBoard.getProductState()))
                .title(marketBoard.getTitle())
                .price(marketBoard.getPrice())
                .views(marketBoard.getViews())
                .likes(marketBoard.getLikes())
                .reports(marketBoard.getReports())
                .visible(marketBoard.getVisible())
                .pictureUrl(marketBoard.getPictureUrl())
                .build();
    }
}
