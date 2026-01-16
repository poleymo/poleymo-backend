package com.market.board.controller;

import com.market.board.service.ProductStatusService;
import com.market.board.dto.ProductStatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("market/product/status")
@RestController
@RequiredArgsConstructor
public class ProductStatusController {

    private final ProductStatusService productStatusService;

    @GetMapping("list")
    public List<ProductStatusDto.Response> getAllProductStatus() {
        return productStatusService.findAll().stream()
                .map(ProductStatusDto::from)
                .toList();
    }
}
