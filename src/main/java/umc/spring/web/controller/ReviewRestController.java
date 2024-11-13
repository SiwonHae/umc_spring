package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.review.ReviewConverter;
import umc.spring.converter.store.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.web.dto.review.ReviewRequestDTO;
import umc.spring.web.dto.review.ReviewResponseDTO.RegisterResultDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {
    private final ReviewCommandService reviewCommandService;

    @PostMapping
    public ApiResponse<RegisterResultDTO> register(@RequestBody @Valid ReviewRequestDTO.RegisterReviewDTO request) {
        Review review = reviewCommandService.registerReview(request);
        return ApiResponse.onSuccess(ReviewConverter.toRegisterResultDTO(review));
    }

}
