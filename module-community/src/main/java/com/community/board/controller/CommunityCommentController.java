package com.community.board.controller;

import com.community.board.service.CommunityCommentService;
import com.community.board.dto.CommunityCommentDto;
import com.community.board.dto.PageResponse;
import dto.AuthedUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequiredArgsConstructor
@RequestMapping("community/reply")
@RestController
public class CommunityCommentController {

    private final CommunityCommentService commentService;

    @GetMapping
    public PageResponse<CommunityCommentDto.Response> findReplies(Long communitySeq, int page, int size) {
        Page<CommunityCommentDto.Response> map = commentService.findCommentList(communitySeq, page, size)
                .map(CommunityCommentDto::from);
        return PageResponse.from(map);
    }

    @PostMapping
    public CommunityCommentDto.Response saveReply(@AuthenticationPrincipal AuthedUserDto user, Long communitySeq, CommunityCommentDto.Request dto) {
        return CommunityCommentDto.from(commentService.saveComment(user, communitySeq, dto));
    }

    @PatchMapping
    public CommunityCommentDto.Response updateReply(@AuthenticationPrincipal AuthedUserDto user, CommunityCommentDto.Update dto) {
        if (!Objects.equals(user.getAuthSeq(), dto.getUser())) {
            throw new IllegalArgumentException("허용되지 않은 요청입니다.");
        }
        return CommunityCommentDto.from(commentService.updateComment(dto));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteReply(@AuthenticationPrincipal AuthedUserDto user, CommunityCommentDto.Delete dto) {
        if (!Objects.equals(user.getAuthSeq(), dto.getUser())) {
            throw new IllegalArgumentException("허용되지 않은 요청입니다.");
        }

        commentService.deleteComment(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
