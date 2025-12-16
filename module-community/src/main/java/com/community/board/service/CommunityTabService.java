package com.community.board.service;

import com.community.board.service.dto.CommunityTabDto;
import com.community.board.service.entity.CommunityTab;
import com.community.board.service.repository.CommunityTabRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityTabService {
    private final CommunityTabRepository communityTabRepository;

    public CommunityTab saveTab(CommunityTabDto.Request communityTab) {
        CommunityTab build = CommunityTab.builder()
                .tabName(communityTab.getTabName())
                .build();
        return communityTabRepository.save(build);
    }

    public CommunityTab getTab(Long tabId) {
        return communityTabRepository.findById(tabId)
                .orElseThrow(() -> new IllegalArgumentException("해당 탭이 없음"));
    }

    public List<CommunityTab> getAllTab() {
        return communityTabRepository.findAll();
    }
}
