package com.jeong.strestserver.member.dto;

import com.jeong.strestserver.member.entity.Gender;
import lombok.Data;

import java.util.Date;


@Data
public class MemberRequestDto {

    private String email;
    private String password;
    private String name;
    private Date birthdate;
    private Gender gender;
}
