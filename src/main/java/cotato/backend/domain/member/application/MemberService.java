package cotato.backend.domain.member.application;

import cotato.backend.domain.member.dto.request.MemberUpdateInfoRequest;
import cotato.backend.domain.member.dto.request.MemberUpdateRoleRequest;
import cotato.backend.domain.member.dto.response.MemberResponse;
import cotato.backend.domain.member.dto.response.OperationResponse;
import cotato.backend.domain.member.entity.Member;

public interface MemberService {
    MemberResponse getMember(Long id);

    OperationResponse getOperation(Long id);

    Member updateMemberInfo(Long memberId, MemberUpdateInfoRequest request);

    Member updateMemberRole(Long id, MemberUpdateRoleRequest request);
}
