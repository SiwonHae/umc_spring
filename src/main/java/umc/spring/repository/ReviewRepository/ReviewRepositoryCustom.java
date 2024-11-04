package umc.spring.repository.ReviewRepository;

import umc.spring.domain.Review;

public interface ReviewRepositoryCustom {
    Review insertReview(String title, String body, Float score, Long memberId, Long storeId);
}
