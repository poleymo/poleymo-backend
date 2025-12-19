package com.community.board.service;

import com.community.board.service.dto.CommunityDto;
import com.community.board.service.entity.Community;
import com.community.board.service.entity.CommunityTab;
import com.community.board.service.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private static final long INIT = 0;
    private final CommunityRepository communityRepository;
    private final CommunityTabService communityTabService;

    public Community find(Long communityId) {
        return communityRepository.findById(communityId).orElseThrow(() -> new IllegalArgumentException(""));
    }

    public Page<Community> find(Long boardType, int page, int size) {
        //todo: 이 부분 파라미터로 받는거 고려
        Sort sort = Sort.by("communitySeq").descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return communityRepository.findAllByCommunityTab_CommunityTabSeq(boardType, pageable);
    }

    @Transactional
    public Community save(CommunityDto.Request dto) {
        CommunityTab tab = communityTabService.getTab(dto.getCommunityTabSeq());

        Community community = Community.builder()
                .communityTab(tab)
                .title(dto.getTitle())
                .content(dto.getContent())
                .recommend(INIT)
                .author(dto.getAuthor())
                .visible(true)
                .build();
        return communityRepository.save(community);
    }

    /**
     * 일반적인 상황에서는 글 제목과 내용만 수정 가능하도록 제한</br>
     * 탭 수정은 다른 메서드로 분리
     * </br>
     * 업데이트 권한이 있는지 검중 추가 필요
     * */
    @Transactional
    public Community update(CommunityDto.Update dto) {
        Community community = communityRepository.findById(dto.getCommunitySeq())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글"));

        community.changeTitle(dto.getTitle());
        community.changeContent(dto.getContent());
        community.changeVisible(dto.getVisible());
        return community;
    }

    /**
     * 업데이트 권한이 있는지 검중 추가 필요
     */
    @Transactional
    public void delete(CommunityDto.Delete dto) {
        communityRepository.deleteById(dto.getCommunitySeq());
    }
}
