package cotato.backend.domain.application.dto.response;

import cotato.backend.domain.application.entity.Application;
import cotato.backend.domain.application.entity.Part;
import cotato.backend.domain.member.entity.Member;

public record FilterApplicationResponse(
        String name,
        int period,
        Part part,
        int likeCount
) {
    public static FilterApplicationResponse from(Application application){
        Member member = application.getMember();
        int likeCount = application.getLikes().size();

        return new FilterApplicationResponse(
                member.getName(),
                application.getPeriod(),
                application.getPart(),
                likeCount
        );
    }
}
