package com.market.board.service;

import com.market.board.dto.ProductStateDto;
import com.market.board.entity.ProductState;
import com.market.board.repository.ProductStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductStateService {

    private final ProductStateRepository productStateRepository;

    public ProductState save(ProductStateDto.Request productState) {
        ProductState build = ProductState.builder()
                .prdState(productState.getPrdState())
                .visible(true)
                .build();
        return productStateRepository.save(build);
    }

    public ProductState find(Long psSeq) {
        return productStateRepository.findById(psSeq)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품 상태가 없습니다."));
    }

    public List<ProductState> findAll() {
        return productStateRepository.findAll();
    }
}
