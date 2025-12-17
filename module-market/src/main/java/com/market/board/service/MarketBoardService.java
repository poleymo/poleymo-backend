package com.market.board.service;

import com.market.board.service.dto.MarketBoardDto;
import com.market.board.service.entity.MarketBoard;
import com.market.board.service.repository.MarketBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketBoardService {

    private final MarketBoardRepository marketBoardRepository;

    public MarketBoard saveMarketBoard(MarketBoardDto.Request marketBoard) {
        MarketBoard build = MarketBoard.builder()
                .userSeq(marketBoard.getUserSeq())
                .bsSeq(marketBoard.getBsSeq())
                .psSeq(marketBoard.getPsSeq())
                .title(marketBoard.getTitle())
                .price(marketBoard.getPrice())
                .view(marketBoard.getView())
                .like(marketBoard.getLike())
                .activated(marketBoard.isActivated())
                .reported(marketBoard.getReported())
                .build();
        return marketBoardRepository.save(build);
    }

    public List<MarketBoard> getAllBoard() {
        return marketBoardRepository.findAll();
    }
}
