package com.market.board.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MarketBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mbSeq; // 중고나라 게시글 키 (marketBoardSequence)


    // 게시글 작성자 키, 게시글 상태 키, 게시된 물품 상태 키는 각각
    // User, BoardState, ProductState로 class를 생성하고, foreign key 설정을 해야한다.

    private int userSeq; // 중고나라 게시글 작성자 키
    private int bsSeq; // 중고나라 게시글 상태 키 (board_state_seq)
    private int psSeq; // 게시된 물품 상태 키 (product_state_seq)
    private String title; // 중고나라 게시글 제목
    private int price; // 물품 가격

    @Column(name = "view_count")
    private int view; // 중고나라 게시글 조회 수

    @Column(name = "like_count")
    private int like; // 중고나라 게시글 좋아요 횟수
    private boolean activated; // 중고나라 게시글 활성화 여부
    private int reported; // 중고나라 게시글 신고 횟수
//    private String prdTag; // 상품 태그 (일단 구현 x) (테이블 or 컬럼, 적용 방식 논의 필요)

    public MarketBoard(int userSeq, int bsSeq, int psSeq, String title, int price, int view, int like, boolean activated, int reported) {
        this.userSeq = userSeq;
        this.bsSeq = bsSeq;
        this.psSeq = psSeq;
        this.title = title;
        this.price = price;
        this.view = view;
        this.like = like;
        this.activated = activated;
        this.reported = reported;
    }

    public MarketBoard() {
    }

    public int getMbSeq() {
        return mbSeq;
    }

    public int getuserSeq() {
        return userSeq;
    }

    public int getbsSeq() {
        return bsSeq;
    }

    public int getpsSeq() {
        return psSeq;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public int getView() {
        return view;
    }

    public int getLike() {
        return like;
    }

    public boolean isactivated() {
        return activated;
    }

    public int getReported() {
        return reported;
    }
}
