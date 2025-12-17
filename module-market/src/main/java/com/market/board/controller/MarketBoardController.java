package com.market.board.controller;


import com.market.board.service.MarketBoardService;
import com.market.board.service.dto.MarketBoardDto;
import com.market.board.service.entity.MarketBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("market")
@RestController
@RequiredArgsConstructor
public class MarketBoardController {

    private final MarketBoardService marketBoardService;

    @PostMapping
    public MarketBoardDto.Response saveMarketBoard(@RequestBody MarketBoardDto.Request marketBoardDto) {
        return toResponse(marketBoardService.saveMarketBoard(marketBoardDto));
    }

    @GetMapping
    public List<MarketBoardDto.Response> getAllMarketBoard() {
        return marketBoardService.getAllBoard().stream()
                .map(this::toResponse)
                .toList();
    }

    private MarketBoardDto.Response toResponse(MarketBoard marketBoard) {
        return MarketBoardDto.Response.builder()
                .mbSeq(marketBoard.getMbSeq())
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
    }
}
