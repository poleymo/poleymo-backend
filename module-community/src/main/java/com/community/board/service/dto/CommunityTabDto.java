package com.community.board.service.dto;

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
}
