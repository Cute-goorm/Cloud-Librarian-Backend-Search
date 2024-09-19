package Cloud.Book_Search_Backend_API.service;

import Cloud.Book_Search_Backend_API.BookRepository.MemberRepository;
import Cloud.Book_Search_Backend_API.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // 서비스 클래스를 빈으로 등록합니다.
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired // 생성자 주입을 위한 @Autowired 어노테이션 추가
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getLoginMemberByLoginId(String loginId) {
        // loginId를 사용해 Member 객체를 조회
        Optional<Member> member = memberRepository.findByLoginId(loginId);
        if (member.isPresent()) {
            return member.get(); // 올바른 클래스 사용
        } else {
            throw new IllegalArgumentException("해당 로그인 ID를 가진 사용자를 찾을 수 없습니다.");
        }
    }

    public Member getLoginMemberById(Long memberId) {
        // memberId를 사용해 Member 객체를 조회
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isPresent()) {
            return member.get(); // 올바른 클래스 사용
        } else {
            throw new IllegalArgumentException("해당 ID를 가진 사용자를 찾을 수 없습니다.");
        }
    }
}
