package com.market.board.controller;

import com.market.board.service.ProductStateService;
import com.market.board.dto.ProductStateDto;
import com.market.board.entity.ProductState;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("market/product/state")
@RestController
@RequiredArgsConstructor
public class ProductStateController {

    private final ProductStateService productStateService;

    @PostMapping
    public ProductStateDto.Response saveProductState(@RequestBody ProductStateDto.Request productStateDto) {
        return ProductStateDto.from(productStateService.save(productStateDto));
    }

    @GetMapping("list")
    public List<ProductStateDto.Response> getAllProductState() {
        return productStateService.findAll().stream()
                .map(ProductStateDto::from)
                .toList();
    }

    @GetMapping
    public ProductStateDto.Response getProductState(Long psSeq) {
        return ProductStateDto.from(productStateService.find(psSeq));
    }
}
