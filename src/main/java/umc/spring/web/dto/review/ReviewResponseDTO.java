package umc.spring.web.dto.review;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterResultDTO {
        Long reviewId;
        LocalDateTime createdAt;
    }
}
