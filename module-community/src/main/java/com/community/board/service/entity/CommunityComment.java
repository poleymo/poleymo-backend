package com.community.board.service.entity;

import com.community.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CommunityComment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentSeq;

    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    private CommunityComment parent;

    @ManyToOne
    private Community community;

    private String content;

    private Boolean visible;

    private Long recommend;
//클로저 테이블 변경 고려
    public Long getParentSeq() {
        if (parent == null) {
            return null;
        }
        return parent.getCommentSeq();
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeVisible(Boolean visible) {
        this.visible = visible;
    }

    public void changeRecommend(Long recommend) {
        this.recommend = recommend;
    }
}

