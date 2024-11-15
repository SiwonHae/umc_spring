package umc.spring.service.MemberService;

import java.util.Optional;
import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Review;

public interface MemberQueryService {
    Optional<Member> findMember(Long id);
    Page<Review> getReviewList(Long StoreId, Integer page);
}
