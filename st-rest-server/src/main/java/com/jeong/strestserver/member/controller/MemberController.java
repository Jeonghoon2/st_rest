package com.jeong.strestserver.member.controller;

import com.jeong.strestserver.member.dto.MemberRequestDto;
import com.jeong.strestserver.member.dto.MemberResponseDto;
import com.jeong.strestserver.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /* C - Create */
    @PostMapping("/register")
    public ResponseEntity<MemberResponseDto.SaveMember> createMember(@RequestBody MemberRequestDto memberRequestDto) {
        MemberResponseDto.SaveMember member = memberService.createMember(memberRequestDto);
        return ResponseEntity.ok().body(member);
    }

    /* R - Read */
    @GetMapping("/all")
    public ResponseEntity<List<MemberResponseDto.SaveMember>> getAllMembers() {
        List<MemberResponseDto.SaveMember> members = memberService.getAllMembers();
        return ResponseEntity.ok().body(members);
    }

    /* U - Update */
    @PutMapping("/update")
    public ResponseEntity<MemberResponseDto.SaveMember> updateMember(@RequestBody MemberRequestDto memberRequestDto) {
        MemberResponseDto.SaveMember updatedMember = memberService.updateMember(memberRequestDto);
        return ResponseEntity.ok().body(updatedMember);
    }

    /* D - Delete */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMember(@RequestParam Integer id) {
        String msg = memberService.deleteMember(id);
        return ResponseEntity.ok().body(msg);
    }

}
