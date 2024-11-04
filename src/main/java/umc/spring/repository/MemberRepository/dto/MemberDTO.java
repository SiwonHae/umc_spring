package umc.spring.repository.MemberRepository.dto;

import lombok.Data;

@Data
public class MemberDTO {
    private Long id;
    private String name;
    private String email;
    private Integer point;
}