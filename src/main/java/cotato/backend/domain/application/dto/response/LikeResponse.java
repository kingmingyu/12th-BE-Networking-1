package cotato.backend.domain.application.dto.response;

public record LikeResponse(
        int likeCount,
        boolean isLiked
) {
}
