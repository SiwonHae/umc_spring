package umc.spring.service.MemberService;

import java.util.Optional;
import umc.spring.domain.Member;

public interface MemberQueryService {
    Optional<Member> findMember(Long id);
}
