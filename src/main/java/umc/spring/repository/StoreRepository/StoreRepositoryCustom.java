package umc.spring.repository.StoreRepository;

import java.util.List;
import umc.spring.domain.Store;

public interface StoreRepositoryCustom {
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
}