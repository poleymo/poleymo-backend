package com.market.board.controller;

import com.market.board.service.SaleStatusService;
import com.market.board.dto.SaleStatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("market/board/state")
@RestController
@RequiredArgsConstructor
public class SaleStatusController {

    private final SaleStatusService saleStatusService;

    @PostMapping
    public SaleStatusDto.Response saveSaleStatus(@RequestBody SaleStatusDto.Request saleStatusDto) {
        return SaleStatusDto.from(saleStatusService.save(saleStatusDto));
    }

    @GetMapping("list")
    public List<SaleStatusDto.Response> getAllSaleStatus() {
        return saleStatusService.findAll().stream()
                .map(SaleStatusDto::from)
                .toList();
    }

    @GetMapping
    public SaleStatusDto.Response getSaleStatus(Long ssSeq) {
        return SaleStatusDto.from(saleStatusService.find(ssSeq));
    }
}
