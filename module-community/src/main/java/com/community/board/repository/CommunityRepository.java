package com.community.board.repository;

import com.community.board.entity.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommunityRepository extends JpaRepository<Community, Long> {
    Page<Community> findAllByCommunityTab_CommunityTabSeq(Long communityTabCommunityTabSeq, Pageable pageable);
}
