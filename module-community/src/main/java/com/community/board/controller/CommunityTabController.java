package com.community.board.controller;

import com.community.board.service.CommunityTabService;
import com.community.board.service.dto.CommunityTabDto;
import com.community.board.service.entity.CommunityTab;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("community/tabs")
@RestController
public class CommunityTabController {

    private final CommunityTabService communityTabService;

    @GetMapping
    public List<CommunityTabDto.Response> getAllTab() {
        return communityTabService.getAllTab().stream()
                .map(CommunityTabDto::from)
                .toList();
    }

    @PostMapping
    public CommunityTabDto.Response saveTab(@RequestBody CommunityTabDto.Request tab) {
        CommunityTab communityTab = communityTabService.saveTab(tab);
        return CommunityTabDto.from(communityTab);
    }
}
