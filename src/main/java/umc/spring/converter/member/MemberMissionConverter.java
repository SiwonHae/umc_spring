package umc.spring.converter.member;

import java.time.LocalDateTime;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.web.dto.member.MemberMissionRequestDTO.RegisterMissionDTO;
import umc.spring.web.dto.member.MemberMissionResponseDTO;
import umc.spring.web.dto.member.MemberMissionResponseDTO.RegisterResultDTO;

public class MemberMissionConverter {

    public static MemberMissionResponseDTO.RegisterResultDTO toRegisterResultDTO(MemberMission memberMission) {
        return RegisterResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MemberMission toMemberMission(RegisterMissionDTO request, MemberQueryService memberQueryService, MissionQueryService missionQueryService) {
        Member member = memberQueryService.findMember(request.getMemberId()).get();
        Mission mission = missionQueryService.findMission(request.getMissionId()).get();

        return MemberMission.builder()
                .status(MissionStatus.CHALLENGING)
                .member(member)
                .mission(mission)
                .build();
    }
}
