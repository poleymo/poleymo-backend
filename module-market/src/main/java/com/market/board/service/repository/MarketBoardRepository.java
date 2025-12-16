package com.market.board.service.repository;

import com.market.board.service.entity.MarketBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketBoardRepository extends JpaRepository<MarketBoard, Integer> {
}
