package com.market.board.repository;

import com.market.board.entity.ProductState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStateRepository extends JpaRepository<ProductState, Integer> {
}
