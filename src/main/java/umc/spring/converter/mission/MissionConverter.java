package umc.spring.converter.mission;

import java.time.LocalDateTime;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.web.dto.mission.MissionRequestDTO.RegisterMissionDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;
import umc.spring.web.dto.mission.MissionResponseDTO.RegisterResultDTO;

public class MissionConverter {

    public static MissionResponseDTO.RegisterResultDTO toRegisterResultDTO(Mission mission) {
        return RegisterResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(RegisterMissionDTO request, StoreQueryService storeQueryService) {
        Store store = storeQueryService.findStore(request.getStoreId()).get();

        return Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .store(store)
                .build();
    }
}