package cotato.backend.domain.application.dao;

import cotato.backend.domain.application.entity.Application;
import cotato.backend.domain.application.entity.Like;
import cotato.backend.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByMemberAndApplication(Member member, Application application);
}
