package com.market.board.controller;

import com.market.board.service.MarketBoardStateService;
import com.market.board.service.dto.MarketBoardStateDto;
import com.market.board.service.entity.MarketBoardState;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("market/board/state")
@RestController
@RequiredArgsConstructor
public class MarketBoardStateController {

    private final MarketBoardStateService marketBoardStateService;

    @PostMapping
    public MarketBoardStateDto.Response saveMarketBoardState(@RequestBody MarketBoardStateDto.Request marketBoardStateDto) {
        return toResponse(marketBoardStateService.saveMarketBoardState(marketBoardStateDto));
    }

    @GetMapping("list")
    public List<MarketBoardStateDto.Response> getAllMarketBoardState() {
        return marketBoardStateService.getAllMarketBoardState().stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping
    public MarketBoardStateDto.Response getMarketBoardState(int mbsSeq) {
        return toResponse(marketBoardStateService.getMarketBoardState(mbsSeq));
    }

    private MarketBoardStateDto.Response toResponse(MarketBoardState marketBoardState) {
        return MarketBoardStateDto.Response.builder()
                .mbsSeq(marketBoardState.getMbsSeq())
                .mbState(marketBoardState.getMbState())
                .build();
    }
}
