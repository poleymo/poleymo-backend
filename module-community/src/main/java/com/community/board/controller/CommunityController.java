package com.community.board.controller;

import com.community.board.service.CommunityService;
import com.community.board.dto.CommunityDto;
import com.community.board.dto.PageResponse;
import com.community.board.entity.Community;
 import dto.AuthedUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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
    public PageResponse<CommunityDto.Response> getCommunityList(@PathVariable Long type, int page, int size) {
        return PageResponse.from(communityService.find(type, page, size));
    }

    @PostMapping
    public CommunityDto.Response saveCommunity(@AuthenticationPrincipal AuthedUserDto user,
                                               @RequestBody CommunityDto.Request dto) {
        Community community = communityService.save(user, dto);
        return CommunityDto.from(community);
    }

    @PatchMapping
    public CommunityDto.Response updateCommunity(@AuthenticationPrincipal AuthedUserDto user, @RequestBody CommunityDto.Update dto) {
        if (!Objects.equals(user.getAuthSeq(), dto.getUser())) {
            throw new IllegalArgumentException("허용되지 않은 요청입니다.");
        }
        return communityService.update(dto);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteCommunity(@AuthenticationPrincipal AuthedUserDto user, @RequestBody CommunityDto.Delete dto) {
        if (!Objects.equals(user.getAuthSeq(), dto.getUser())) {
            throw new IllegalArgumentException("허용되지 않은 요청입니다.");
        }

        communityService.delete(dto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
