package com.community.board.service.dto;

import com.community.board.service.entity.CommunityTab;
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
    }

    public static CommunityTabDto.Response from(CommunityTab communityTab) {
        return CommunityTabDto.Response
                .builder()
                .communityTabSeq(communityTab.getCommunityTabSeq())
                .tabName(communityTab.getTabName())
                .build();
    }
}
