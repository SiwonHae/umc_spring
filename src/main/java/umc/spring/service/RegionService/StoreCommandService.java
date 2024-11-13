package umc.spring.service.RegionService;

import umc.spring.domain.Store;
import umc.spring.web.dto.store.StoreRequestDTO.RegisterStoreDTO;

public interface StoreCommandService {
    Store registerStore(RegisterStoreDTO request);
}
