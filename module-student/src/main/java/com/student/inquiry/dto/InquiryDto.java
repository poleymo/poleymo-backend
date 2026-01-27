package com.student.inquiry.dto;

import com.student.inquiry.entity.Inquiry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class InquiryDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Request {
        private final Long inquirySeq;
        private final String title;
        private final String content;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private final Long inquirySeq;
        private final String title;
        private final String content;
    }

    public static Response from(Inquiry inquiry) {
        return Response.builder()
                .inquirySeq(inquiry.getInquirySeq())
                .content(inquiry.getContent())
                .title(inquiry.getTitle())
                .build();
    }
}
