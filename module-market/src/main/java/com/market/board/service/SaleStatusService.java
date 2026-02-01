package com.market.board.service;

import com.market.board.entity.SaleStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleStatusService {

    public List<SaleStatus> findAll() {
        return Arrays.asList(SaleStatus.values());
    }
}