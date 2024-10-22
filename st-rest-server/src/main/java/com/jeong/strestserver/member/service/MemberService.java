package com.jeong.strestserver.member.service;

import com.jeong.strestserver.member.dto.MemberRequestDto;
import com.jeong.strestserver.member.dto.MemberResponseDto;
import com.jeong.strestserver.member.entity.Member;
import com.jeong.strestserver.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    /* 사용자 삭제 */
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

    /* 모든 사용자 조회 */
    public List<MemberResponseDto.SaveMember> getAllMembers() {

        /* 모든 Member 엔티티를 데이터베이스에서 조회 */
        List<Member> members = memberRepository.findAll();

        /* Member 엔티티 목록을 MemberResponseDto.SaveMember로 변환하여 반환 */
        return members.stream()
                .map(MemberResponseDto.SaveMember::new)
                .collect(Collectors.toList());
    }

    /* 사용자 업데이트 */
    @Transactional
    public MemberResponseDto.SaveMember updateMember(MemberRequestDto memberRequestDto) {

        /* 사용자 조회 */
        Member findMember = memberRepository.findById(memberRequestDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("해당 번호의 사용자를 찾을 수 없습니다. " + memberRequestDto.getId()));

        /* 사용자 업데이트 */
        findMember.updateMember(memberRequestDto);

        return new MemberResponseDto.SaveMember(findMember);
    }

    /* 사용자 삭제 */
    @Transactional
    public String deleteMember(Integer memberId) {
        /* 삭제할 사용자가 있는 지 조회*/
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("해당 번호의 사용자를 찾을 수 없습니다. " + memberId));

        memberRepository.deleteById(memberId);

        return "정상적으로 사용자를 삭제 하였습니다.";
    }


}
