package com.community.board.service;

import com.community.board.dto.CommunityTabDto;
import com.community.board.entity.CommunityTab;
import com.community.board.repository.CommunityTabRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 수정 권한 확인필요 </br>
     * 일반 회원권한 이상
     */
    @Transactional
    public CommunityTab updateTab(CommunityTabDto.Update dto) {
        CommunityTab communityTab = communityTabRepository.findById(dto.getCommunityTabSeq())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 탭"));

        communityTab.changeTabName(dto.getTabName());
        communityTab.changeVisible(dto.getVisible());
        return communityTab;
    }

    /**
     * 수정 권한 확인필요 </br>
     * 일반 회원권한 이상
     */
    @Transactional
    public void deleteTab(CommunityTabDto.Delete dto) {
        communityTabRepository.deleteById(dto.getCommunityTabSeq());
    }
}
