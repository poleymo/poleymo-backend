package com.market.board.controller;


import com.market.board.service.MarketBoardService;
import com.market.board.service.dto.MarketBoardDto;
import com.market.board.service.entity.MarketBoard;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("market")
@RestController
public class MarketBoardController {

    private final MarketBoardService boardService;

    public MarketBoardController(MarketBoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public String saveMarketBoard(@RequestBody MarketBoardDto dto) {
        boardService.saveMarketBoard(dto);
        return "saveMarketBoard ok";
    }

    @GetMapping
    public List<MarketBoard> getAllMarketBoard() {
        return boardService.getBoard();
    }
}
