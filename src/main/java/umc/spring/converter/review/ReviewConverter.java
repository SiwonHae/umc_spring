package umc.spring.converter.review;

import java.time.LocalDateTime;
import umc.spring.domain.Member;
import umc.spring.domain.Region;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.web.dto.review.ReviewRequestDTO.RegisterReviewDTO;
import umc.spring.web.dto.review.ReviewResponseDTO.RegisterResultDTO;

public class ReviewConverter {

    public static RegisterResultDTO toRegisterResultDTO(Review review) {
        return RegisterResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(RegisterReviewDTO request, MemberQueryService memberQueryService, StoreQueryService storeQueryService) {
        Member member = memberQueryService.findMember(request.getMemberId()).get();
        Store store = storeQueryService.findStore(request.getStoreId()).get();

        return Review.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .score(request.getScore())
                .member(member)
                .store(store)
                .build();
    }
}