package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.store.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.service.RegionService.StoreCommandService;
import umc.spring.web.dto.store.StoreRequestDTO;
import umc.spring.web.dto.store.StoreResponseDTO.RegisterResultDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {
    private final StoreCommandService storeCommandService;

    @PostMapping
    public ApiResponse<RegisterResultDTO> register(@RequestBody @Valid StoreRequestDTO.RegisterStoreDTO request) {
        Store store = storeCommandService.registerStore(request);
        return ApiResponse.onSuccess(StoreConverter.toRegisterResultDTO(store));
    }
}
