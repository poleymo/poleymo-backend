package com.market.board.service;

import com.market.board.service.dto.MarketBoardDto;
import com.market.board.service.entity.MarketBoard;
import com.market.board.service.repository.MarketBoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketBoardService {

    private final MarketBoardRepository repository;

    public MarketBoardService(MarketBoardRepository repository) {
        this.repository = repository;
    }

    public MarketBoard saveMarketBoard(MarketBoardDto dto) {
        MarketBoard board = new MarketBoard(dto.getuserSeq(), dto.getbsSeq(), dto.getpsSeq(), dto.getTitle(), dto.getPrice(), dto.getView(), dto.getLike(), dto.isactivated(), dto.getReported());
        return repository.save(board);
    }

    public List<MarketBoard> getBoard() {
        return repository.findAll();
    }
}
