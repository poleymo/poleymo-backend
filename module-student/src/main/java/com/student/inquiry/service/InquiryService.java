package com.student.inquiry.service;

import com.student.inquiry.dto.InquiryDto;
import com.student.inquiry.entity.Inquiry;
import com.student.inquiry.repository.InquiryAnswerRepository;
import com.student.inquiry.repository.InquiryRepository;
import dto.AuthedUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InquiryService {
    private final InquiryRepository inquiryRepository;
    private final InquiryAnswerRepository inquiryAnswerRepository;

    @Transactional
    public Inquiry saveInquiry(AuthedUserDto user, InquiryDto.Request dto) {
        Inquiry inquiry = Inquiry.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .authorSeq(user.getAuthSeq())
                .build();
        return inquiryRepository.save(inquiry);
    }

    @Transactional
    public Inquiry updateInquiry(AuthedUserDto user, InquiryDto.Request dto) {
        Inquiry inquiry = findInquiry(dto.getInquirySeq());
        if (!Objects.equals(inquiry.getAuthorSeq(), user.getAuthSeq())) {
            throw new IllegalArgumentException("허용되지 않은 요청");
        }

        inquiry.changeTitle(dto.getTitle());
        inquiry.changeContent(dto.getContent());
        return inquiryRepository.save(inquiry);
    }

    public Inquiry findInquiry(Long inquiryId) {
        return inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new IllegalArgumentException("inquiry not found"));
    }

    public Page<Inquiry> findAllInquiry(Integer size, Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return inquiryRepository.findAll(pageable);
    }

    @Transactional
    public void softDelete(AuthedUserDto user, InquiryDto.Request dto) {
        Inquiry inquiry = findInquiry(dto.getInquirySeq());
        if (!Objects.equals(inquiry.getAuthorSeq(), user.getAuthSeq())) {
            throw new IllegalArgumentException("허용되지 않은 요청");
        }
        inquiry.changeVisible(false);
    }
}
