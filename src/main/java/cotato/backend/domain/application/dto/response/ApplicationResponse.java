package cotato.backend.domain.application.dto.response;

import cotato.backend.domain.application.entity.Application;
import cotato.backend.domain.application.entity.Part;
import cotato.backend.domain.member.entity.Member;

import java.time.LocalDateTime;

public record ApplicationResponse(
        String name,
        int period,
        int age,
        Part part,
        int ability,
        int passion,
        String phoneNumber,
        LocalDateTime applicationTime
) {
    public static ApplicationResponse from(Application application){
        Member member = application.getMember();

        return new ApplicationResponse(
                member.getName(),
                application.getPeriod(),
                member.getAge(),
                application.getPart(),
                application.getAbility(),
                application.getPassion(),
                member.getPhoneNumber(),
                application.getApplicationTime()
        );
    }
}
