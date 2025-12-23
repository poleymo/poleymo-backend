package com.community.board.dto;

import com.community.board.entity.CommunityComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class CommunityCommentDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Request {
        private String author;// 사용자
        private Long communitySeq;// 게시글 아이디
        private Long parentId; // 부모 아이디
        private String content; // 내용
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private String author;
        private Long communitySeq;
        private Long parentSeq;
        private Long commentSeq;
        private String content;
        private Boolean visible;
        private Long recommend;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Update {
        private String author;//변경 불가
        private Long communitySeq;//변경 불가
        private Long parentSeq;//변경 불가
        private Long commentSeq;//변경 불가
        private String content;
        private Boolean visible;
        private Long recommend;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Delete {
        private String author;
        private Long communitySeq;
        private Long parentSeq;
        private Long commentSeq;
        private String content;
        private Boolean visible;
        private Long recommend;
    }

    public static Response from(CommunityComment comment) {
        return Response.builder()
                .author(comment.getAuthor())
                .communitySeq(comment.getCommentSeq())
                .parentSeq(comment.getParentSeq())
                .commentSeq(comment.getCommentSeq())
                .content(comment.getContent())
                .visible(comment.getVisible())
                .recommend(comment.getRecommend())
                .build();
    }
}
