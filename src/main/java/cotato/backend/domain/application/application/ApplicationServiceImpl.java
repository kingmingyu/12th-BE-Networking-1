package cotato.backend.domain.application.application;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.application.dao.ApplicationRepository;
import cotato.backend.domain.application.dto.request.ApplicationRequest;
import cotato.backend.domain.application.dto.response.ApplicationResponse;
import cotato.backend.domain.application.entity.Application;
import cotato.backend.domain.member.dao.MemberRepository;
import cotato.backend.domain.member.entity.Member;
import cotato.backend.domain.member.entity.Role;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationServiceImpl implements ApplicationService{

    private final ApplicationRepository applicationRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long saveApplication(ApplicationRequest request) {
        Member member = Member.builder()
                .name(request.name())
                .age(request.age())
                .phoneNumber(request.phoneNumber())
                .role(Role.GUEST)
                .build();

        Member saveMember = memberRepository.save(member);

        Application application = Application.builder()
                .period(request.period())
                .part(request.part())
                .ability(request.ability())
                .passion(request.passion())
                .member(member)
                .build();

        Application saveApplication = applicationRepository.save(application);
        return saveApplication.getId();
    }

    @Override
    public ApplicationResponse findById(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));
        return ApplicationResponse.from(application);
    }
}
