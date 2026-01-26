package com.community.board.service;

import com.community.board.dto.CommunityCommentDto;
import com.community.board.entity.CommunityComment;
import com.community.board.repository.CommunityCommentRepository;
import com.community.board.repository.CommunityRepository;
import dto.AuthedUserDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

//todo 사용자 인증 연동 필요
@Service
@RequiredArgsConstructor
public class CommunityCommentService {
    private final CommunityCommentRepository commentRepository;
    private final CommunityRepository communityRepository;

    public Page<CommunityComment> findCommentList(Long communitySeq, int page, int size) {
        //추후 생성일로 전환
        Sort sort = Sort.by(Sort.Direction.DESC, "commentSeq");
        Pageable pageable = PageRequest.of(page, size, sort);

        return commentRepository
                .findAllByCommunity_CommunitySeqAndVisible(communitySeq, true, pageable);
    }

    public CommunityComment saveComment(AuthedUserDto user, Long communitySeq, CommunityCommentDto.Request dto) {
        CommunityComment parentComment = null;
        if (dto.getParentId() != null) {
            parentComment = commentRepository.findById(dto.getParentId()).orElse(null);
        }
        CommunityComment comment = CommunityComment.builder()
                .user(user.getAuthSeq())
                .parent(parentComment)
                .content(dto.getContent())
                .community(communityRepository.getReferenceById(communitySeq)) // 프록시 엔티티
                //여기서 Id 제외 커뮤니티 필드 조회하면 터짐
                .build();

        return commentRepository.save(comment);
    }

    @Transactional
    public CommunityComment updateComment(CommunityCommentDto.Update dto) {
        CommunityComment comment = commentRepository.findById(dto.getCommentSeq())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글"));
        comment.changeContent(dto.getContent());
        comment.changeVisible(dto.getVisible());
        comment.changeRecommend(dto.getRecommend());
        return comment;
    }

    @Transactional
    public void deleteComment(CommunityCommentDto.Delete dto) {
        commentRepository.deleteById(dto.getCommentSeq());
    }
}
