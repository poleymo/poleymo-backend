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
        return toResponse(productStateService.saveProductState(productStateDto));
    }

    @GetMapping("list")
    public List<ProductStateDto.Response> getAllProductState() {
        return productStateService.getAllProductState().stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping
    public ProductStateDto.Response getProductState(int psSeq) {
        return toResponse(productStateService.getProductState(psSeq));
    }

    private ProductStateDto.Response toResponse(ProductState productState) {
        return ProductStateDto.Response.builder()
                .psSeq(productState.getPsSeq())
                .prdState(productState.getPrdState())
                .visible(productState.getVisible())
                .build();
    }
}
