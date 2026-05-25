package cotato.backend.api.controller;

import cotato.backend.api.dto.response.DefaultIdResponse;
import cotato.backend.common.dto.DataResponse;
import cotato.backend.domain.application.application.ApplicationQueryService;
import cotato.backend.domain.application.application.ApplicationService;
import cotato.backend.domain.application.dto.request.ApplicationRequest;
import cotato.backend.domain.application.dto.response.ApplicationResponse;
import cotato.backend.domain.application.dto.response.FilterApplicationResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ApplicationQueryService applicationQueryService;

    // 지원서 정보 등록
    @PostMapping
    public ResponseEntity<DataResponse<DefaultIdResponse>> save(@RequestBody ApplicationRequest request){
        return ResponseEntity.ok(
                DataResponse.created(
                        DefaultIdResponse.of(applicationService.saveApplication(request))
                )
        );
    }

    // 지원서 정보 단건 조회
    @GetMapping("{id}")
    public ResponseEntity<DataResponse<ApplicationResponse>> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                DataResponse.from(applicationService.findById(id))
        );
    }

    @GetMapping
    public ResponseEntity<DataResponse<Page<FilterApplicationResponse>>> getFilterApplication(
            @RequestParam String query,
            @RequestParam String type,
            @PageableDefault(size = 10) Pageable pageable
    ){
        Page<FilterApplicationResponse> result = applicationQueryService.getFilterApplication(query, type, pageable);
        return ResponseEntity.ok(
                DataResponse.from(result)
        );
    }
}
