package cotato.backend.domain.application.dto.request;

import cotato.backend.domain.application.entity.Part;

import java.time.LocalDateTime;

public record ApplicationRequest(
        String name,
        int period,
        Part part,
        int age,
        int ability,
        int passion,
        String phoneNumber
) {
}
