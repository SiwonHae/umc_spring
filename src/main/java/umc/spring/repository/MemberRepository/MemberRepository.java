package umc.spring.repository.MemberRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
