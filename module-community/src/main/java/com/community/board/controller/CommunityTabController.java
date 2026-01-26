package com.community.board.controller;

import com.community.board.service.CommunityTabService;
import com.community.board.dto.CommunityTabDto;
import com.community.board.entity.CommunityTab;
import dto.AuthedUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
    public CommunityTabDto.Response saveTab(@AuthenticationPrincipal AuthedUserDto user, @RequestBody CommunityTabDto.Request dto) {
        if (!Objects.equals(user.getRole(), "ROLE_ADMIN")) {
            throw new IllegalArgumentException("요청 권한이 없습니다.");
        }
        CommunityTab communityTab = communityTabService.saveTab(dto);
        return CommunityTabDto.from(communityTab);
    }

    @PatchMapping
    public CommunityTabDto.Response updateTab(@AuthenticationPrincipal AuthedUserDto user, @RequestBody CommunityTabDto.Update dto) {
        if (!Objects.equals(user.getRole(), "ROLE_ADMIN")) {
            throw new IllegalArgumentException("요청 권한이 없습니다.");
        }
        CommunityTab communityTab = communityTabService.updateTab(dto);
        return CommunityTabDto.from(communityTab);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteTab(@AuthenticationPrincipal AuthedUserDto user, @RequestBody CommunityTabDto.Delete dto) {
        if (!Objects.equals(user.getRole(), "ROLE_ADMIN")) {
            throw new IllegalArgumentException("요청 권한이 없습니다.");
        }
        communityTabService.deleteTab(dto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
