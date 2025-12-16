package com.community.board.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class CommunityDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Request {
        private String title;
        private String content;
        private String author;
        private Long communityTabSeq;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private Long communitySeq;
        private String title;
        private String content;
        private Long recommend;
        private String author;
        private Long communityTabSeq;
    }
}
