package com.student.inquiry.controller;

import com.student.inquiry.dto.InquiryDto;
import com.student.inquiry.dto.PageResponse;
import com.student.inquiry.entity.Inquiry;
import com.student.inquiry.service.InquiryService;
import dto.AuthedUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("inquiries")
@RestController
public class InquiryController {

    private final InquiryService inquiryService;

    @GetMapping
    public PageResponse<InquiryDto.Response> findAll(Integer page, Integer size) {
        Page<Inquiry> allInquiry = inquiryService.findAllInquiry(size, page);
        Page<InquiryDto.Response> items = allInquiry.map(InquiryDto::from);
        return PageResponse.from(items);
    }

    @PostMapping
    public InquiryDto.Response save(@AuthenticationPrincipal AuthedUserDto user, @RequestBody InquiryDto.Request dto) {
        return InquiryDto.from(inquiryService.saveInquiry(user, dto));
    }

    @PatchMapping
    public InquiryDto.Response update(@AuthenticationPrincipal AuthedUserDto user, @RequestBody InquiryDto.Request dto) {
        return InquiryDto.from(inquiryService.updateInquiry(user, dto));
    }

    @DeleteMapping
    public void delete(@AuthenticationPrincipal AuthedUserDto user, @RequestBody InquiryDto.Request dto) {
        inquiryService.softDelete(user, dto);
    }
}
