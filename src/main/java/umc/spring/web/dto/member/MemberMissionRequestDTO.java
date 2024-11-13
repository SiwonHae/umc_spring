package umc.spring.web.dto.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.validation.annotation.ExistMissions;
import umc.spring.validation.annotation.ExistStores;

public class MemberMissionRequestDTO {

    @Getter
    public static class RegisterMissionDTO {
        @NotNull
        Long memberId;
        @NotNull
        @ExistMissions
        Long missionId;
    }
}