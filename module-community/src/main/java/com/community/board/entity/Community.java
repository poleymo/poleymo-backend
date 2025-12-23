package com.community.board.entity;

import com.community.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Community extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long communitySeq;

    private String title;

    private  String content;

    private Long recommend;

    private String author;

    private Boolean visible;

    @ManyToOne(fetch = FetchType.LAZY)
    private CommunityTab communityTab;

    public Long getCommunityTabSeq() {
        return communityTab.getCommunityTabSeq();
    }

    public void changeTitle(String title) {
        if (Objects.equals(this.title, title)) {
            return;
        }
        this.title = title;
    }

    public void changeContent(String content) {
        if (Objects.equals(this.content, content)) {
            return;
        }
        this.content = content;
    }

    public void changeVisible(Boolean visible) {
        if (Objects.equals(this.visible, visible)) {
            return;
        }
        this.visible = visible;
    }
}
