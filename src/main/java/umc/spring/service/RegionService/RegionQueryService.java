package umc.spring.service.RegionService;

import java.util.Optional;
import umc.spring.domain.Region;

public interface RegionQueryService {
    Optional<Region> findRegion(Long id);
}
