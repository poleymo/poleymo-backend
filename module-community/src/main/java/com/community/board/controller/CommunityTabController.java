package com.community.board.controller;

import com.community.board.service.CommunityTabService;
import com.community.board.service.dto.CommunityTabDto;
import com.community.board.service.entity.CommunityTab;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public CommunityTabDto.Response saveTab(@RequestBody CommunityTabDto.Request dto) {
        CommunityTab communityTab = communityTabService.saveTab(dto);
        return CommunityTabDto.from(communityTab);
    }

    @PatchMapping
    public CommunityTabDto.Response updateTab(@RequestBody CommunityTabDto.Update dto) {
        CommunityTab communityTab = communityTabService.updateTab(dto);
        return CommunityTabDto.from(communityTab);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteTab(@RequestBody CommunityTabDto.Delete dto) {
        communityTabService.deleteTab(dto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
