package com.jeong.strestserver.member.controller;

import com.jeong.strestserver.member.dto.MemberRequestDto;
import com.jeong.strestserver.member.dto.MemberResponseDto;
import com.jeong.strestserver.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<MemberResponseDto.SaveMember> createMember(@RequestBody MemberRequestDto memberRequestDto) {
        MemberResponseDto.SaveMember member = memberService.createMember(memberRequestDto);
        return ResponseEntity.ok().body(member);
    }


}
