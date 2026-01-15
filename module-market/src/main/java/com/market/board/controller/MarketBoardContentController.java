package com.market.board.controller;

import com.market.board.dto.MarketBoardContentDto;
import com.market.board.entity.MarketBoardContent;
import com.market.board.service.MarketBoardContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("market/board/content")
@RestController
@RequiredArgsConstructor
public class MarketBoardContentController {

    private final MarketBoardContentService marketBoardContentService;

    @PostMapping
    public MarketBoardContentDto.Response saveMarketBoardContent(@RequestBody MarketBoardContentDto.Request marketBoardContentDto) {
        return MarketBoardContentDto.from(marketBoardContentService.save(marketBoardContentDto));
    }

    @GetMapping("list")
    public List<MarketBoardContentDto.Response> getAllMarketBoardContent() {
        return marketBoardContentService.findAll().stream()
                .map(MarketBoardContentDto::from)
                .toList();
    }

    @GetMapping
    public MarketBoardContentDto.Response getMarketBoardContent(Long mbcSeq) {
        return MarketBoardContentDto.from(marketBoardContentService.find(mbcSeq));
    }
}
