package umc.spring.converter.store;

import java.time.LocalDateTime;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.service.RegionService.RegionQueryService;
import umc.spring.web.dto.store.StoreRequestDTO.RegisterStoreDTO;
import umc.spring.web.dto.store.StoreResponseDTO.RegisterResultDTO;

public class StoreConverter {

    public static RegisterResultDTO toRegisterResultDTO(Store store){
        return RegisterResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Store toStore(RegisterStoreDTO request, RegionQueryService regionQueryService){
        Region region = regionQueryService.findRegion(request.getRegionId()).get();

        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region(region)
                .build();
    }
}