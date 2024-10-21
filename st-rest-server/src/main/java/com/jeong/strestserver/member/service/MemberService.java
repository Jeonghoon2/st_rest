package com.jeong.strestserver.member.service;

import com.jeong.strestserver.member.controller.MemberController;
import com.jeong.strestserver.member.dto.MemberRequestDto;
import com.jeong.strestserver.member.dto.MemberResponseDto;
import com.jeong.strestserver.member.entity.Member;
import com.jeong.strestserver.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto.SaveMember createMember(MemberRequestDto memberRequestDto) {

        /* 사용자 중복 체크 */
        Optional<Member> findMember = memberRepository.findByEmail(memberRequestDto.getEmail());

        /* DB에 저장할 사용자 생성 */
        Member member = new Member(
                memberRequestDto.getEmail(),
                memberRequestDto.getPassword(),
                memberRequestDto.getName(),
                memberRequestDto.getBirthdate(),
                memberRequestDto.getGender()
        );

        /* DB에 저장*/
        Member newMember = memberRepository.save(member);

        /* 반환할 사용자 리턴*/
        return new MemberResponseDto.SaveMember(newMember);

    }


}
