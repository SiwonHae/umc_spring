package umc.spring.repository.MissionRepository.dto;

import lombok.Data;

@Data
public class HomeDTO {
    private String storeName;
    private Long remainingDays;
    private Integer rewardPoints;
    private String regionName;
}
