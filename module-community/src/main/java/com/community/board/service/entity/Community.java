package com.community.board.service.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long communitySeq;

    private String title;

    private  String content;

    private Long recommend;

    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    private CommunityTab communityTab;

    public Long getCommunityTabSeq() {
        return communityTab.getCommunityTabSeq();
    }
}
