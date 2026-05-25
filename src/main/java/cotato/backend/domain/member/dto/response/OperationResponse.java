package cotato.backend.domain.member.dto.response;

import cotato.backend.domain.member.entity.Member;
import cotato.backend.domain.member.entity.Role;

public record OperationResponse(
        String name,
        int age,
        String phoneNumber,
        Role role
) {
    public static OperationResponse from(Member member){
        return new OperationResponse(
               member.getName(),
            member.getAge(),
            member.getPhoneNumber(),
            member.getRole()
        );
    };
}
