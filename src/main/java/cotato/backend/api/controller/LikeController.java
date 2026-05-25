package cotato.backend.api.controller;

import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.application.application.LikeService;
import cotato.backend.domain.application.dto.request.LikeRequest;
import cotato.backend.domain.application.dto.response.LikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/api/applications/{applicationId}/like")
    public ResponseEntity<DataResponse<LikeResponse>> pushLike(@PathVariable Long applicationId, @RequestParam LikeRequest request){
        return ResponseEntity.ok(
                DataResponse.from(likeService.toggleLike(applicationId, request))
        );
    }
}
