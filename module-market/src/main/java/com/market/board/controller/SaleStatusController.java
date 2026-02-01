package com.market.board.controller;

import com.market.board.service.SaleStatusService;
import com.market.board.dto.SaleStatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("market/board/status")
@RestController
@RequiredArgsConstructor
public class SaleStatusController {

    private final SaleStatusService saleStatusService;

    @GetMapping("list")
    public List<SaleStatusDto.Response> getAllSaleStatus() {
        return saleStatusService.findAll().stream()
                .map(SaleStatusDto::from)
                .toList();
    }
}