package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.review.ReviewConverter;
import umc.spring.converter.store.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.RegionService.RegionQueryService;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.web.dto.review.ReviewRequestDTO.RegisterReviewDTO;
import umc.spring.web.dto.store.StoreRequestDTO.RegisterStoreDTO;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;

    private final MemberQueryService memberQueryService;
    private final StoreQueryService storeQueryService;

    @Override
    @Transactional
    public Review registerReview(RegisterReviewDTO request) {
        Review newReview = ReviewConverter.toReview(request, memberQueryService, storeQueryService);

        return reviewRepository.save(newReview);
    }
}