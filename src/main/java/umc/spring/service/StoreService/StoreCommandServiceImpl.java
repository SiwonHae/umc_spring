package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.store.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.service.RegionService.RegionQueryService;
import umc.spring.web.dto.store.StoreRequestDTO.RegisterStoreDTO;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionQueryService regionQueryService;

    @Override
    @Transactional
    public Store registerStore(RegisterStoreDTO request) {
        Store newStore = StoreConverter.toStore(request, regionQueryService);

        return storeRepository.save(newStore);
    }
}