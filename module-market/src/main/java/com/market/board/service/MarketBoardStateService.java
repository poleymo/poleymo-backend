package com.market.board.service;

import com.market.board.dto.MarketBoardStateDto;
import com.market.board.entity.MarketBoardState;
import com.market.board.repository.MarketBoardStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketBoardStateService {

    private final MarketBoardStateRepository marketBoardStateRepository;

    public MarketBoardState saveMarketBoardState(MarketBoardStateDto.Request marketBoardState) {
        MarketBoardState build = MarketBoardState.builder()
                .mbState(marketBoardState.getMbState())
                .build();
        return marketBoardStateRepository.save(build);
    }

    public MarketBoardState getMarketBoardState(int mbsSeq) {
        return marketBoardStateRepository.findById(mbsSeq)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 상태가 없습니다."));
    }

    public List<MarketBoardState> getAllMarketBoardState() {
        return marketBoardStateRepository.findAll();
    }
}
