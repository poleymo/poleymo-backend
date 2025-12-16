package com.community.board.controller;

import com.community.board.service.CommunityTabService;
import com.community.board.service.dto.CommunityTabDto;
import com.community.board.service.entity.CommunityTab;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("tabs")
@RestController
public class CommunityTabController {

    private final CommunityTabService communityTabService;

    @GetMapping
    public List<CommunityTabDto.Response> getAllTab() {
        return communityTabService.getAllTab().stream()
                .map(this::toResponse)
                .toList();
    }

    @PostMapping
    public CommunityTabDto.Response saveTab(@RequestBody CommunityTabDto.Request tab) {
        return toResponse(communityTabService.saveTab(tab));
    }

    private CommunityTabDto.Response toResponse(CommunityTab communityTab) {
        return CommunityTabDto.Response
                .builder()
                .communityTabSeq(communityTab.getCommunityTabSeq())
                .tabName(communityTab.getTabName())
                .build();
    }
}
