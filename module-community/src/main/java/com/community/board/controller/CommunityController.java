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
        Community community = communityService.find(communitySeq);
        return CommunityDto.from(community);
    }

    @GetMapping("list/{type}")
    public Page<CommunityDto.Response> getCommunityList(@PathVariable Long type, int page, int size) {
        Page<Community> communities = communityService.find(type, page, size);
        return communities.map(CommunityDto::from);
    }

    @PostMapping
    public CommunityDto.Response saveCommunity(@RequestBody CommunityDto.Request communityDto) {
        Community community = communityService.save(communityDto);
        return CommunityDto.from(community);
    }
}
