package com.community.board.dto;

import com.community.board.entity.Community;
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
        private Long user;// 사용자
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
        private Long user;// 사용자
        private Boolean visible;
        private CommunityTabDto.Response communityTabSeq;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Update {
        private Long communitySeq;
        private String title;
        private String content;
        private Long recommend;
        private Long user;
        private Boolean visible;
        private CommunityTabDto.Response communityTabDto;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Delete {
        private Long communitySeq;
        private String title;
        private String content;
        private Long recommend;
        private Long user;
        private Boolean visible;
    }

    public static CommunityDto.Response from(Community community) {
        return CommunityDto.Response.builder()
                .communitySeq(community.getCommunitySeq())
                .communityTabSeq(CommunityTabDto.from(community.getCommunityTab()))
                .title(community.getTitle())
                .content(community.getContent())
                .recommend(community.getRecommend())
                .user(community.getUser())
                .visible(community.getVisible())
                .build();
    }
}
