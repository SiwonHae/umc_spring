package umc.spring.repository.MemberRepository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.QMember;
import umc.spring.repository.MemberRepository.dto.MemberDTO;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QMember member = QMember.member;

    @Override
    public MemberDTO getMemberInfoForMyPage(Long memberId) {

        return jpaQueryFactory.select(
                        Projections.constructor(MemberDTO.class,
                                member.id,
                                member.name,
                                member.email,
                                member.point
                        )
                )
                .from(member)
                .where(member.id.eq(memberId))
                .fetchOne();
    }
}
