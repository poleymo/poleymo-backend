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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MarketBoardService {

    private static final Long INIT = 0L;
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
                .pictureUrl(dto.getPictureUrl())
                .build();
        return marketBoardRepository.save(build);
    }

    public Page<MarketBoard> find(int page, int size) {
        Sort sort = Sort.by("mbSeq").descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return marketBoardRepository.findAll(pageable);
    }

    public MarketBoard find(Long mbSeq) {
        return marketBoardRepository.findById(mbSeq)
                .orElseThrow(() -> new IllegalArgumentException("헤당 게시글이 없습니다."));
    }

    @Transactional
    public MarketBoard update(MarketBoardDto.Update dto) {
        MarketBoard marketBoard = marketBoardRepository.findById(dto.getMbSeq())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        marketBoard.changeMarketBoardState(marketBoardStateService.find(dto.getMbsSeq()));
        marketBoard.changeProductState(productStateService.find(dto.getPsSeq()));
        marketBoard.changeTitle(dto.getTitle());
        marketBoard.changePrice(dto.getPrice());
        marketBoard.changeVisible(dto.getVisible());
        marketBoard.changePictureUrl(dto.getPictureUrl());
        return marketBoard;
    }

    @Transactional
    public void delete(MarketBoardDto.Delete dto) {
        MarketBoard marketBoard = marketBoardRepository.findById(dto.getMbSeq())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        
        List<MarketBoardContent> marketBoardContents = marketBoardContentRepository.findAll().stream()
                .filter(content -> Objects.equals(content.getMbSeq(), dto.getMbSeq()))
                .toList();

        // MarketBoardContent soft-delete
        for (MarketBoardContent marketBoardContent : marketBoardContents) {
            marketBoardContent.changeVisible(false);
        }

        // MarketBoard soft-delete
        marketBoard.changeVisible(false);
    }
}
