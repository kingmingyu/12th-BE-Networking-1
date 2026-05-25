package cotato.backend.domain.member.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MemberUpdateInfoRequest(
        @NotNull
        @Size(min = 2, max = 10, message = "이름은 2글자 이상 10글자 이하로 입력해주세요")
        String name,

        @NotNull
        int age,

        @NotNull
        @Size(min = 11, max = 11, message = "전화번호 형식에 맞춰 입력해주세요(11자)")
        String phoneNumber
) {
}
