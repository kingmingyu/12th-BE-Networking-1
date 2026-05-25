package cotato.backend.domain.application.application;

import com.querydsl.core.types.Predicate;
import cotato.backend.domain.application.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicationQueryDsl {

    Page<Application> getFilterApplication(
            Predicate predicate, String sortType, Pageable pageable
    );
}
