package com.market.board.service;

import com.market.board.dto.SaleStatusDto;
import com.market.board.entity.SaleStatus;
import com.market.board.repository.SaleStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleStatusService {

    private final SaleStatusRepository saleStatusRepository;

    public SaleStatus save(SaleStatusDto.Request saleStatus) {
        SaleStatus build = SaleStatus.builder()
                .mbState(saleStatus.getMbState())
                .visible(true)
                .build();
        return saleStatusRepository.save(build);
    }

    public SaleStatus find(Long ssSeq) {
        return saleStatusRepository.findById(ssSeq)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 상태가 없습니다."));
    }

    public List<SaleStatus> findAll() {
        return saleStatusRepository.findAll();
    }
}
