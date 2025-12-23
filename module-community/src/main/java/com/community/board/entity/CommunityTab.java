package com.community.board.entity;

import com.community.util.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CommunityTab extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long communityTabSeq;

    private String tabName;

    private Boolean visible;

    public void changeVisible(Boolean visible) {
        if (Objects.equals(this.visible, visible)) {
            return;
        }
        this.visible = visible;
    }

    public void changeTabName(String tabName) {
        if (Objects.equals(this.tabName, tabName)) {
            return;
        }
        this.tabName = tabName;
    }
}
