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

    public Community save(CommunityDto.Request dto) {
        CommunityTab tab = communityTabService.getTab(dto.getCommunityTabSeq());

        Community community = Community.builder()
                .communityTab(tab)
                .title(dto.getTitle())
                .content(dto.getContent())
                .recommend(INIT)
                .author(dto.getAuthor())
                .build();
        return communityRepository.save(community);
    }
}
