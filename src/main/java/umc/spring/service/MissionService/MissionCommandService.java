package umc.spring.service.MissionService;

import umc.spring.domain.Mission;
import umc.spring.web.dto.mission.MissionRequestDTO.RegisterMissionDTO;

public interface MissionCommandService {
    Mission registerMission(RegisterMissionDTO request);
}
