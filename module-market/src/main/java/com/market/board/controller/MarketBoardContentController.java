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
        return toResponse(marketBoardContentService.saveMarketBoardContent(marketBoardContentDto));
    }

    @GetMapping
    public List<MarketBoardContentDto.Response> getAllMarketBoardContent() {
        return marketBoardContentService.getAllMarketBoardContent().stream()
                .map(this::toResponse)
                .toList();
    }

    private MarketBoardContentDto.Response toResponse(MarketBoardContent marketBoardContent) {
        return MarketBoardContentDto.Response.builder()
                .mbcSeq(marketBoardContent.getMbcSeq())
                .mbSeq(marketBoardContent.getMbSeq())
                .content(marketBoardContent.getContent())
                .build();
    }
}
