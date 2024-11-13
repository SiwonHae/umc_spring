package umc.spring.service.MissionService;

import java.util.Optional;
import umc.spring.domain.Mission;

public interface MissionQueryService {
    Optional<Mission> findMission(Long id);
}
