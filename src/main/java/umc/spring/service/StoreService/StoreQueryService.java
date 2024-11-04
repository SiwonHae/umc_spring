package umc.spring.service.StoreService;

import java.util.List;
import java.util.Optional;
import umc.spring.domain.Store;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, Float score);
}