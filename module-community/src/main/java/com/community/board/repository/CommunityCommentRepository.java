package com.community.board.repository;

import com.community.board.entity.CommunityComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityCommentRepository extends JpaRepository<CommunityComment, Long> {
    Page<CommunityComment> findAllByCommunity_CommunitySeq(Long communityCommunitySeq, Pageable pageable);
    Page<CommunityComment> findAllByCommunity_CommunitySeqAndVisible(Long communityCommunitySeq, Boolean visible, Pageable pageable);
}
