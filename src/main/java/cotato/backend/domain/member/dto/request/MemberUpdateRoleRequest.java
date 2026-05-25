package cotato.backend.domain.member.dto.request;

import cotato.backend.domain.member.entity.Role;

public record MemberUpdateRoleRequest(
        Role role
) {
}
