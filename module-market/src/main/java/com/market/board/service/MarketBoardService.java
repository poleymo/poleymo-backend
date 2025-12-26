package com.market.board.service;

import com.market.board.dto.MarketBoardDto;
import com.market.board.entity.MarketBoard;
import com.market.board.entity.MarketBoardState;
import com.market.board.entity.ProductState;
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

    public MarketBoard save(MarketBoardDto.Request marketBoard) {
        MarketBoardState mbState = marketBoardStateService.getMarketBoardState(marketBoard.getMbsSeq());
        ProductState productState = productStateService.getProductState(marketBoard.getPsSeq());

        MarketBoard build = MarketBoard.builder()
                .userSeq(marketBoard.getUserSeq())
                .marketBoardState(mbState)
                .productState(productState)
                .title(marketBoard.getTitle())
                .price(marketBoard.getPrice())
                .view(INIT)
                .like(INIT)
                .activated(true)
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
}
