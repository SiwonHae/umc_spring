package umc.spring.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Mission;
import umc.spring.domain.QMember;
import umc.spring.domain.QMission;
import umc.spring.domain.QRegion;
import umc.spring.domain.QStore;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QMemberMission;
import umc.spring.repository.MissionRepository.dto.HomeDTO;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QMission mission = QMission.mission;
    private final QMemberMission memberMission = QMemberMission.memberMission;
    private final QMember member = QMember.member;
    private final QStore store = QStore.store;
    private final QRegion region = QRegion.region;

    @Override
    public List<Mission> findMemberMissionsWithChallenging(Long memberId, Long cursorId, int limit) {
        BooleanBuilder predicate = new BooleanBuilder();

        predicate.and(memberMission.member.id.eq(memberId));
        predicate.and(memberMission.status.eq(MissionStatus.CHALLENGING));
        predicate.and(mission.id.lt(cursorId));

        return jpaQueryFactory
                .select(mission)
                .from(member)
                .join(memberMission).on(member.id.eq(memberMission.member.id))
                .join(mission).on(mission.id.eq(memberMission.mission.id))
                .join(store).on(mission.store.id.eq(store.id))
                .where(predicate)
                .orderBy(mission.id.desc())
                .limit(limit)
                .fetch();
    }

    @Override
    public List<Mission> findMemberMissionsWithComplete(Long memberId, Long cursorId, int limit) {
        BooleanBuilder predicate = new BooleanBuilder();

        predicate.and(memberMission.member.id.eq(memberId));
        predicate.and(memberMission.status.eq(MissionStatus.COMPLETE));
        predicate.and(mission.id.lt(cursorId));

        return jpaQueryFactory
                .select(mission)
                .from(member)
                .join(memberMission).on(member.id.eq(memberMission.member.id))
                .join(mission).on(mission.id.eq(memberMission.mission.id))
                .join(store).on(mission.store.id.eq(store.id))
                .where(predicate)
                .orderBy(mission.id.desc())
                .limit(limit)
                .fetch();
    }

    @Override
    public List<HomeDTO> findHome(String regionName, Long cursor, Long memberId) {
        return jpaQueryFactory.select(
                        Projections.constructor(HomeDTO.class,
                                mission.id,
                                store.name,
                                mission.reward,
                                mission.missionSpec,
                                Expressions.numberTemplate(Long.class, "DATEDIFF({0}, {1})", mission.deadline, LocalDate.now()).as("days_left")
                        )
                )
                .from(mission)
                .join(store).on(mission.store.id.eq(store.id))
                .join(region).on(store.region.id.eq(region.id))
                .where(region.name.eq(regionName)
                        .and(mission.deadline.gt(LocalDate.now()))
                        .and(mission.id.lt(cursor))
                        .and(mission.id.notIn(JPAExpressions.select(memberMission.mission.id)
                                .from(memberMission)
                                .where(memberMission.member.id.eq(memberId)))
                        ))
                .orderBy(mission.id.desc())
                .limit(10)
                .fetch();
    }
}
