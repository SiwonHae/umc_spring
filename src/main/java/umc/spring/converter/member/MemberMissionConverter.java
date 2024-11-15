package umc.spring.converter.member;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
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
import umc.spring.web.dto.member.MemberMissionResponseDTO.MissionPreViewDTO;
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

    public static MemberMissionResponseDTO.MissionPreViewDTO memberMissionChallengingPreViewDTO(MemberMission memberMission){
        return MemberMissionResponseDTO.MissionPreViewDTO.builder()
                .storeName(memberMission.getMission().getStore().getName())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .reward(memberMission.getMission().getReward())
                .status(memberMission.getStatus())
                .build();
    }

    public static MemberMissionResponseDTO.MissionPreViewListDTO memberMissionChallengingPreViewListDTO(Page<MemberMission> memberMissionList){

        List<MissionPreViewDTO> memberMissionPreViewDTOList = memberMissionList.stream()
                .filter(memberMission -> memberMission.getStatus() == MissionStatus.CHALLENGING)
                .map(MemberMissionConverter::memberMissionChallengingPreViewDTO)
                .collect(Collectors.toList());

        return MemberMissionResponseDTO.MissionPreViewListDTO.builder()
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(memberMissionPreViewDTOList.size())
                .memberMissionList(memberMissionPreViewDTOList)
                .build();
    }

    public static MemberMissionResponseDTO.MissionPreViewListDTO memberMissionCompletePreViewListDTO(Page<MemberMission> memberMissionList){

        List<MissionPreViewDTO> memberMissionPreViewDTOList = memberMissionList.stream()
                .filter(memberMission -> memberMission.getStatus() == MissionStatus.COMPLETE)
                .map(MemberMissionConverter::memberMissionChallengingPreViewDTO)
                .collect(Collectors.toList());

        return MemberMissionResponseDTO.MissionPreViewListDTO.builder()
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(memberMissionPreViewDTOList.size())
                .memberMissionList(memberMissionPreViewDTOList)
                .build();
    }
}
