package cotato.backend.domain.application.application;

import cotato.backend.common.exception.EntityNotFoundException;
import cotato.backend.common.exception.ErrorCode;
import cotato.backend.domain.application.dao.ApplicationRepository;
import cotato.backend.domain.application.dao.LikeRepository;
import cotato.backend.domain.application.dto.request.LikeRequest;
import cotato.backend.domain.application.dto.response.LikeResponse;
import cotato.backend.domain.application.entity.Application;
import cotato.backend.domain.application.entity.Like;
import cotato.backend.domain.member.dao.MemberRepository;
import cotato.backend.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{

    private final LikeRepository likeRepository;
    private final ApplicationRepository applicationRepository;
    private final MemberRepository memberRepository;

    @Override
    public LikeResponse toggleLike(Long applicationId, LikeRequest request) {

        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));
        Member member = memberRepository.findById(request.memberId()).orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUND));

        Optional<Like> existingLike = likeRepository.findByMemberAndApplication(member, application);

        boolean isLiked;

        // 좋아요를 누른 상태라면 -> 좋아요 삭제
        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            isLiked = false;
        }
        // 좋아요를 누르지 않은 상태라면 -> 좋아요
        else {
            Like newLike = Like.builder().
                    member(member).
                    application(application).
                    build();
            likeRepository.save(newLike);
            isLiked = true;
        }
        return new LikeResponse(application.getLikes().size(), isLiked);
    }
}
