package com.market.board.service;

import com.market.board.dto.MarketBoardDto;
import com.market.board.entity.MarketBoard;
import com.market.board.entity.MarketBoardContent;
import com.market.board.entity.MarketBoardState;
import com.market.board.entity.ProductState;
import com.market.board.repository.MarketBoardContentRepository;
import com.market.board.repository.MarketBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketBoardService {

    private static final int INIT = 0;
    private final MarketBoardRepository marketBoardRepository;
    private final MarketBoardStateService marketBoardStateService;
    private final ProductStateService productStateService;
    private final MarketBoardContentRepository marketBoardContentRepository;

    public MarketBoard save(MarketBoardDto.Create dto) {
        MarketBoardState mbState = marketBoardStateService.find(dto.getMbsSeq());
        ProductState productState = productStateService.find(dto.getPsSeq());

        MarketBoard build = MarketBoard.builder()
                .userSeq(dto.getUserSeq())
                .marketBoardState(mbState)
                .productState(productState)
                .title(dto.getTitle())
                .price(dto.getPrice())
                .view(INIT)
                .like(INIT)
                .reported(INIT)
                .visible(true)
                .build();
        return marketBoardRepository.save(build);
    }

    public Page<MarketBoard> find(int page, int size) {
        Sort sort = Sort.by("mbSeq").descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return marketBoardRepository.findAll(pageable);
    }

    public MarketBoard find(int mbSeq) {
        return marketBoardRepository.findById(mbSeq)
                .orElseThrow(() -> new IllegalArgumentException("헤당 게시글이 없습니다."));
    }

    public MarketBoard update(MarketBoardDto.Update dto) {
        MarketBoard marketBoard = marketBoardRepository.findById(dto.getMbSeq())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        // 수정 로직
        // ====MarketBoard.java 참고====
        // mbSeq 게시글 키 marketBoard를 조회하기 위해서 필요하다.
        // userSeq 작성자 변경 x, 하지만 작성자임을 확인할 수 있도록 dto에 담아놓는다.
        // marketBoardState(mbsSeq) 게시글 상태 변경 o
        // productState(psSeq) 게시글 물품 상태 변경 o
        // title 게시글 제목 변경 o
        // price 물품 가격 변경 o
        // view 게시글 조회 수 변경 x -> 비정상적인 조회 수를 가진 게시글은 reported 또는 visible로 관리하도록..
        // like 좋아요 횟수 변경 x -> 비정상적인 좋아요 횟수를 가진 게시글은 reported 또는 visible로 관리하도록..
        // reportetd 신고 횟수 변경 x -> 비정상적인 신고 횟수를 가진 게시글은 visible로 관리하도록..
        // visible 조회 가능 여부 변경 o

        marketBoard.changeMarketBoardState(marketBoardStateService.find(dto.getMbsSeq()));
        marketBoard.changeProductState(productStateService.find(dto.getPsSeq()));
        marketBoard.changeTitle(dto.getTitle());
        marketBoard.changePrice(dto.getPrice());
        marketBoard.changeVisible(dto.isVisible());
        marketBoard = marketBoardRepository.save(marketBoard);
        return marketBoard;
    }

    public void delete(MarketBoardDto.Delete dto) {
        MarketBoard marketBoard = marketBoardRepository.findById(dto.getMbSeq())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        
        List<MarketBoardContent> marketBoardContents = marketBoardContentRepository.findAll().stream()
                .filter(content -> content.getMbSeq() == dto.getMbSeq())
                .toList();

        // MarketBoardContent soft-delete
        for (MarketBoardContent marketBoardContent : marketBoardContents) {
            marketBoardContent.changeVisible(false);
            marketBoardContentRepository.save(marketBoardContent);
        }

        // MarketBoard soft-delete
        marketBoard.changeVisible(false);
        marketBoardRepository.save(marketBoard);
    }
}
