package com.market.board.service;

import com.market.board.service.dto.MarketBoardStateDto;
import com.market.board.service.entity.MarketBoardState;
import com.market.board.service.repository.MarketBoardStateRepository;
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

    public List<MarketBoardState> getAllBoard() {
        return marketBoardStateRepository.findAll();
    }
}
