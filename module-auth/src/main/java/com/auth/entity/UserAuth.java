package com.auth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuth {

    @Id
    @GeneratedValue
    private Long authSeq;
    @Column(nullable = false)
    private String userEmail;
    @Column(nullable = false)
    private String password;
    private Boolean active;

    @ManyToOne
    private User user;
}
