package cotato.backend.domain.member.entity;

import java.util.EnumSet;

public enum Role {
    BACKEND_LEADER, // 백엔드 파트장
    FRONTEND_LEADER, // 프론트엔드 파트장
    PM_LEADER, // 기획 파트장
    DESIGN_LEADER, // 디자인 파트장
    PROMO_LEADER, // 홍보 파트장
    VICE_PRESIDENT, // 부회장
    PRESIDENT, // 회장
    EDU_LEADER, // 교육 파트장


    MEMBER, // (운영진이 아닌) 일반 멤버
    GUEST; // 지원자

    // 운영진 역할 집합
    private static final EnumSet<Role> OPERATIONS_TEAM = EnumSet.of(
        BACKEND_LEADER,
        FRONTEND_LEADER,
        PM_LEADER,
        PROMO_LEADER,
        VICE_PRESIDENT,
        PRESIDENT,
        EDU_LEADER
    );

    // 운영진 api를 위한 운영진 확인 메서드
    public boolean isOperation() {
        return OPERATIONS_TEAM.contains(this);
    }
}
