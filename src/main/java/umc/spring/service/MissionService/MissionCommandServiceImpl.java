package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.mission.MissionConverter;
import umc.spring.converter.review.ReviewConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.web.dto.mission.MissionRequestDTO.RegisterMissionDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;

    private final StoreQueryService storeQueryService;

    @Override
    @Transactional
    public Mission registerMission(RegisterMissionDTO request) {
        Mission newMission = MissionConverter.toMission(request, storeQueryService);

        return missionRepository.save(newMission);
    }
}
