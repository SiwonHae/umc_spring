package umc.spring.repository.MissionRepository;

import java.util.List;
import umc.spring.domain.Mission;
import umc.spring.repository.MissionRepository.dto.HomeDTO;

public interface MissionRepositoryCustom {
    List<Mission> findMemberMissionsWithChallenging(Long memberId, Long cursorId, int limit);
    List<Mission> findMemberMissionsWithComplete(Long memberId, Long cursorId, int limit);
    List<HomeDTO> findHome(String regionName, Long cursor, Long memberId);
}
