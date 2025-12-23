package com.community.board.controller;

import com.community.board.service.CommunityCommentService;
import com.community.board.dto.CommunityCommentDto;
import com.community.board.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public CommunityCommentDto.Response saveReply(Long communitySeq, CommunityCommentDto.Request dto) {
        return CommunityCommentDto.from(commentService.saveComment(communitySeq, dto));
    }

    @PatchMapping
    public CommunityCommentDto.Response updateReply(Long communitySeq, CommunityCommentDto.Update dto) {
        return CommunityCommentDto.from(commentService.updateComment(communitySeq, dto));
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteReply(CommunityCommentDto.Delete dto) {
        commentService.deleteComment(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
