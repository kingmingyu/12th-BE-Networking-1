package cotato.backend.domain.application.application;

import cotato.backend.domain.application.dto.request.LikeRequest;
import cotato.backend.domain.application.dto.response.LikeResponse;

public interface LikeService {

    LikeResponse toggleLike(Long applicationId, LikeRequest request);
}
