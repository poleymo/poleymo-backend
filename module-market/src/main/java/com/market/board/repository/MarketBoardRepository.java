package com.market.board.repository;

import com.market.board.entity.MarketBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketBoardRepository extends JpaRepository<MarketBoard, Long> {
}
