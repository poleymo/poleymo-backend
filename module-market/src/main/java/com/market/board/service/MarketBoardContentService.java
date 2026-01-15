package com.market.board.service;

import com.market.board.dto.MarketBoardContentDto;
import com.market.board.entity.MarketBoard;
import com.market.board.entity.MarketBoardContent;
import com.market.board.repository.MarketBoardContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketBoardContentService {

    private final MarketBoardContentRepository marketBoardContentRepository;
    private final MarketBoardService marketBoardService;

    public MarketBoardContent save(MarketBoardContentDto.Request marketBoardContent) {
        MarketBoard marketBoard = marketBoardService.find(marketBoardContent.getMbSeq());
        MarketBoardContent build = MarketBoardContent.builder()
                .marketBoard(marketBoard)
                .content(marketBoardContent.getContent())
                .visible(true)
                .build();
        return marketBoardContentRepository.save(build);
    }

    public List<MarketBoardContent> findAll() {
        return marketBoardContentRepository.findAll();
    }

    public MarketBoardContent find(Long mbcSeq) {
        return marketBoardContentRepository.findById(mbcSeq)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글의 내용이 없습니다."));
    }
}
