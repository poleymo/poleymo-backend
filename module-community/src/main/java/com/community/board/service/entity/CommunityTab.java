package com.community.board.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CommunityTab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long communityTabSeq;

    private String tabName;
}
