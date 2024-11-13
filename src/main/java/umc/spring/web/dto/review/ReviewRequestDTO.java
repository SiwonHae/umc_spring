package umc.spring.web.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.validation.annotation.ExistStores;

public class ReviewRequestDTO {

    @Getter
    public static class RegisterReviewDTO {
        @NotBlank
        String title;
        @Size(min = 5, max = 100)
        String body;
        @NotNull
        Float score;
        @NotNull
        Long memberId;
        @NotNull
        @ExistStores
        Long storeId;
    }
}