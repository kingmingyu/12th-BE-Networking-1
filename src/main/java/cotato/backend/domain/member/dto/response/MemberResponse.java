package cotato.backend.domain.member.dto.response;

import cotato.backend.domain.member.entity.Member;

public record MemberResponse(
        String name,
        int age,
        String phoneNumber
) {
    public static MemberResponse from(Member member){
        return new MemberResponse(
                member.getName(),
                member.getAge(),
                member.getPhoneNumber()
        );
    }
}
