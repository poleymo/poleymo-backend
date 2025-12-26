package com.market.board.repository;

import com.market.board.entity.MarketBoardContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketBoardContentRepository extends JpaRepository<MarketBoardContent, Integer> {
}
