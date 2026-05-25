package cotato.backend.api.controller;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.member.application.MemberService;
import cotato.backend.domain.member.dto.request.MemberUpdateInfoRequest;
import cotato.backend.domain.member.dto.request.MemberUpdateRoleRequest;
import cotato.backend.domain.member.dto.response.MemberResponse;
import cotato.backend.domain.member.dto.response.OperationResponse;
import cotato.backend.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberController {

    private final MemberService memberService;

    // Member 정보 조회
    @GetMapping("/api/members/{id}")
    public ResponseEntity<DataResponse<MemberResponse>> getMember(@PathVariable Long id){
        return ResponseEntity.ok(
                DataResponse.from(memberService.getMember(id))
        );
    }

    // 운영진 정보 조회
    @GetMapping("/api/operations/{id}")
    public ResponseEntity<DataResponse<OperationResponse>> getOperation(@PathVariable Long id){
        return ResponseEntity.ok(
                DataResponse.from(memberService.getOperation(id))
        );
    }

    // Member 정보 수정(운영진 포함)
    @PutMapping("/api/members/{id}")
    public ResponseEntity<DataResponse<MemberResponse>> updateMemberInfo(@PathVariable Long id, @RequestBody MemberUpdateInfoRequest request){
        Member updateMember = memberService.updateMemberInfo(id, request);

        return ResponseEntity.ok(
                DataResponse.from(MemberResponse.from(updateMember))
        );
    }

    // Member role 변경 (권한??)
    @PatchMapping("/api/members/{id}/role")
    public ResponseEntity<DataResponse<MemberResponse>> updateRole(@PathVariable Long id, @RequestBody MemberUpdateRoleRequest request){
        Member updateMember = memberService.updateMemberRole(id, request);

        return ResponseEntity.ok(
                DataResponse.from(MemberResponse.from(updateMember))
        );
    }
}
