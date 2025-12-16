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

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final CommunityTabService communityTabService;

    public Community find(Long communityId) {
        return communityRepository.findById(communityId).orElseThrow(() -> new IllegalArgumentException(""));
    }

    public List<Community> find(int page, int size) {
        Sort sort = Sort.by("community_seq").descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Community> all = communityRepository.findAll(pageable);
        return all.getContent();
    }

    public Community save(CommunityDto.Request dto) {
        CommunityTab tab = communityTabService.getTab(dto.getCommunityTabSeq());

        System.out.println("CommunityService.save");
        Community community = Community.builder()
                .communityTab(tab)
                .title(dto.getTitle())
                .content(dto.getContent())
                .recommend(0L)
                .author(dto.getAuthor())
                .build();
        return communityRepository.save(community);
    }
}
