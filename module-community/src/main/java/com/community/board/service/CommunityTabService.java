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

    /**
     * 수정 권한 확인필요 </br>
     * 일반 회원권한 이상
     */
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
    public void deleteTab(CommunityTabDto.Delete dto) {
        communityTabRepository.deleteById(dto.getCommunityTabSeq());
    }
}
