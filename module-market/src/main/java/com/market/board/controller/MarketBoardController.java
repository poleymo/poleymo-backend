package com.market.board.controller;


import com.market.board.service.MarketBoardService;
import com.market.board.dto.MarketBoardDto;
import com.market.board.entity.MarketBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("market")
@RestController
@RequiredArgsConstructor
public class MarketBoardController {

    private final MarketBoardService marketBoardService;

    @PostMapping
    public MarketBoardDto.Response saveMarketBoard(@RequestBody MarketBoardDto.Create marketBoardDto) {
        return MarketBoardDto.from(marketBoardService.save(marketBoardDto));
    }

    @GetMapping("list")
    public Page<MarketBoardDto.Response> getMarketBoardList(int page, int size) {
        Page<MarketBoard> marketBoards = marketBoardService.find(page, size);
        return marketBoards.map(MarketBoardDto::from);
    }

    @GetMapping
    public MarketBoardDto.Response getMarketBoard(int mbSeq) {
        return MarketBoardDto.from(marketBoardService.find(mbSeq));
    }

    @PatchMapping
    public MarketBoardDto.Response updateMarketBoard(@RequestBody MarketBoardDto.Update dto) {
        MarketBoard marketBoard = marketBoardService.update(dto);
        return MarketBoardDto.from(marketBoard);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteMarketBoard(@RequestBody MarketBoardDto.Delete dto) {
        marketBoardService.delete(dto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
