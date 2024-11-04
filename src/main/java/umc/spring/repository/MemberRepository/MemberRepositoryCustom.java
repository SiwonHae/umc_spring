package umc.spring.repository.MemberRepository;

import umc.spring.domain.Member;
import umc.spring.repository.MemberRepository.dto.MemberDTO;

public interface MemberRepositoryCustom {
    MemberDTO getMemberInfoForMyPage(Long memberId);
}
