package com.community.board.service.dto;

import com.community.board.service.entity.Community;
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
        private CommunityTabDto.Response communityTabSeq;
    }

    public static CommunityDto.Response from(Community community) {
        return CommunityDto.Response.builder()
                .communitySeq(community.getCommunitySeq())
                .communityTabSeq(CommunityTabDto.from(community.getCommunityTab()))
                .title(community.getTitle())
                .content(community.getContent())
                .recommend(community.getRecommend())
                .author(community.getAuthor())
                .build();
    }
}
