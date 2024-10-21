package com.jeong.strestserver.member.dto;

import com.jeong.strestserver.member.entity.Gender;
import com.jeong.strestserver.member.entity.Member;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

public class MemberResponseDto {

    @Data
    public static class SaveMember{
        private Integer id;
        private String email;
        private String name;
        private Date birthdate;
        private Gender gender;
        private LocalDateTime createdAt;

        public SaveMember(Member member) {
            this.id = member.getId();
            this.email = member.getEmail();
            this.name = member.getName();
            this.birthdate = member.getBirthdate();
            this.gender = member.getGender();
            this.createdAt = member.getCreatedAt();
        }
    }

}
