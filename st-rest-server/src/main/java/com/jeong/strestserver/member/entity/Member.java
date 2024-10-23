package com.jeong.strestserver.member.entity;

import com.jeong.strestserver.member.dto.MemberRequestDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date birthdate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Member(String email, String password, String name, Date birthdate, Gender gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateMember(MemberRequestDto memberRequestDto) {
        this.email = memberRequestDto.getEmail();
        this.name = memberRequestDto.getName();
        this.birthdate = memberRequestDto.getBirthdate();
        this.gender = memberRequestDto.getGender();
        this.updatedAt = LocalDateTime.now();
    }

    public Member() {

    }
}
