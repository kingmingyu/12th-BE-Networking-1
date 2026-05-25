package cotato.backend.domain.application.application;


import cotato.backend.domain.application.dto.request.ApplicationRequest;
import cotato.backend.domain.application.dto.response.ApplicationResponse;

public interface ApplicationService {

    Long saveApplication(ApplicationRequest request);
    ApplicationResponse findById(Long id);
}
