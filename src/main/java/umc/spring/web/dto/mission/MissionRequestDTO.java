package umc.spring.web.dto.mission;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import umc.spring.validation.annotation.ExistStores;

public class MissionRequestDTO {

    @Getter
    public static class RegisterMissionDTO {
        @NotNull
        Integer reward;
        @NotNull
        LocalDate deadline;
        @NotBlank
        @Size(min = 5, max = 100)
        String missionSpec;
        @NotNull
        @ExistStores
        Long storeId;
    }
}