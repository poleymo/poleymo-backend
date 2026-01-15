package com.market.board.controller;

import com.market.board.service.MarketBoardStateService;
import com.market.board.dto.MarketBoardStateDto;
import com.market.board.entity.MarketBoardState;
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
        return MarketBoardStateDto.from(marketBoardStateService.save(marketBoardStateDto));
    }

    @GetMapping("list")
    public List<MarketBoardStateDto.Response> getAllMarketBoardState() {
        return marketBoardStateService.findAll().stream()
                .map(MarketBoardStateDto::from)
                .toList();
    }

    @GetMapping
    public MarketBoardStateDto.Response getMarketBoardState(Long mbsSeq) {
        return MarketBoardStateDto.from(marketBoardStateService.find(mbsSeq));
    }


}
