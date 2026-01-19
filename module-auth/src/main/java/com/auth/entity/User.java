package com.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue
    private Long userSeq;

    private String userName;

    private Date birthday;

    //개인 정보
    @Column(nullable = false, unique = true)
    private String userEmail;

    private String userPhone;

    //학교 정보
    //사용자 정보와 학교 정보로 나눠도 될 것 같음
    private Integer grade; //학년

    @Column(unique = true)
    private Long studentId; //학번

    private String subject; //학과

    private String campus;

    private Boolean active;

    public void changeUserName(String userName) {
        this.userName = userName;
    }

    public void changeUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void changeUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void changeGrade(Integer grade) {
        this.grade = grade;
    }

    public void changeStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void changeSubject(String subject) {
        this.subject = subject;
    }

    public void changeCampus(String campus) {
        this.campus = campus;
    }

    public void changeBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void changeActive(boolean active) {
        this.active = active;
    }
}
