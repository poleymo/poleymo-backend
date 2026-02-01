package com.market.board.service;

import com.market.board.dto.MarketBoardDto;
import com.market.board.entity.MarketBoard;
import com.market.board.entity.MarketBoardContent;
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
    private final MarketBoardContentRepository marketBoardContentRepository;

    public MarketBoard save(MarketBoardDto.Create dto) {
        if (dto.getSaleStatus() == null) {
            throw new IllegalArgumentException("게시글 상태는 필수 입력 항목입니다.");
        }
        if (dto.getProductStatus() == null) {
            throw new IllegalArgumentException("상품 상태는 필수 입력 항목입니다.");
        }

        MarketBoard build = MarketBoard.builder()
                .userSeq(dto.getUserSeq())
                .saleStatus(dto.getSaleStatus())
                .productStatus(dto.getProductStatus())
                .title(dto.getTitle())
                .price(dto.getPrice())
                .views(INIT)
                .likes(INIT)
                .reports(INIT)
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

        if (dto.getSaleStatus() == null) {
            throw new IllegalArgumentException("게시글 상태는 필수 입력 항목입니다.");
        }
        if (dto.getProductStatus() == null) {
            throw new IllegalArgumentException("상품 상태는 필수 입력 항목입니다.");
        }

        marketBoard.changeSaleStatus(dto.getSaleStatus());
        marketBoard.changeProductStatus(dto.getProductStatus());
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
