package com.community.board.service.repository;

import com.community.board.service.entity.CommunityComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityCommentRepository extends JpaRepository<CommunityComment, Long> {
    Page<CommunityComment> findAllByCommunity_CommunitySeq(Long communityCommunitySeq, Pageable pageable);
}
