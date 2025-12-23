package com.community.board.dto;

import com.community.board.entity.CommunityTab;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class CommunityTabDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Request {
        private String tabName;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private Long communityTabSeq;
        private String tabName;
        private Boolean visible;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Update {
        private Long communityTabSeq;
        private String tabName;
        private Boolean visible;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Delete {

        private Long communityTabSeq;
        private String tabName;
        private Boolean visible;
    }

    public static CommunityTabDto.Response from(CommunityTab communityTab) {
        return CommunityTabDto.Response
                .builder()
                .communityTabSeq(communityTab.getCommunityTabSeq())
                .tabName(communityTab.getTabName())
                .visible(communityTab.getVisible())
                .build();
    }
}
