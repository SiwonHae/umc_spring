package umc.spring.service.ReviewService;

import umc.spring.domain.Review;
import umc.spring.web.dto.review.ReviewRequestDTO.RegisterReviewDTO;

public interface ReviewCommandService {
    Review registerReview(RegisterReviewDTO request);
}
