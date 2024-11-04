package umc.spring.repository.ReviewRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Member;
import umc.spring.domain.QMember;
import umc.spring.domain.QStore;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final EntityManager entityManager;
    private final JPAQueryFactory jpaQueryFactory;
    private final QMember qMember = QMember.member;
    private final QStore qStore = QStore.store;

    @Override
    @Transactional
    public Review insertReview(String title, String body, Float score, Long memberId, Long storeId) {

        Member member = jpaQueryFactory.selectFrom(qMember)
                .where(qMember.id.eq(memberId))
                .fetchOne();

        Store store = jpaQueryFactory.selectFrom(qStore)
                .where(qStore.id.eq(storeId))
                .fetchOne();

        Review review = Review.builder()
                .title(title)
                .body(body)
                .score(score)
                .member(member)
                .store(store)
                .build();

        entityManager.persist(review);
        return review;
    }
}
