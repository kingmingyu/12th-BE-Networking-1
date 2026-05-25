package cotato.backend.domain.member.application;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.member.dao.MemberRepository;
import cotato.backend.domain.member.dto.request.MemberUpdateInfoRequest;
import cotato.backend.domain.member.dto.request.MemberUpdateRoleRequest;
import cotato.backend.domain.member.dto.response.MemberResponse;
import cotato.backend.domain.member.dto.response.OperationResponse;
import cotato.backend.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public MemberResponse getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));
        return MemberResponse.from(member);
    }

    @Override
    @Transactional(readOnly = true)
    public OperationResponse getOperation(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));
        return OperationResponse.from(member);
    }

    @Override
    public Member updateMemberInfo(Long memberId, MemberUpdateInfoRequest request) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));
        member.updateMemberInfo(
                request.name(),
                request.age(),
                request.phoneNumber()
        );

        return member;
    }

    @Override
    public Member updateMemberRole(Long id, MemberUpdateRoleRequest request) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));
        member.updateRole(request.role());
        return member;
    }
}
