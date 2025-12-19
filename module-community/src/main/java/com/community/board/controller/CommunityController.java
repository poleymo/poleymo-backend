package com.community.board.controller;

import com.community.board.service.CommunityService;
import com.community.board.service.dto.CommunityDto;
import com.community.board.service.entity.Community;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequestMapping("community/boards")
@RestController
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping
    public CommunityDto.Response getCommunity(Long communitySeq) {
        return toResponse(communityService.find(communitySeq));
    }

    @GetMapping("list/{type}")
    public Page<CommunityDto.Response> getCommunityList(@PathVariable Long type, int page, int size) {
        Page<Community> communities = communityService.find(type, page, size);

        return communities.map(this::toResponse);
    }

    @PostMapping
    public CommunityDto.Response saveCommunity(@RequestBody CommunityDto.Request communityDto) {
        return toResponse(communityService.save(communityDto));
    }

    private CommunityDto.Response toResponse(Community community) {
        return CommunityDto.Response.builder()
                .communitySeq(community.getCommunitySeq())
                .communityTabSeq(community.getCommunityTabSeq())
                .title(community.getTitle())
                .content(community.getContent())
                .recommend(community.getRecommend())
                .author(community.getAuthor())
                .build();
    }
}
