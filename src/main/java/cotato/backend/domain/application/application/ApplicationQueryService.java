package cotato.backend.domain.application.application;

import com.querydsl.core.BooleanBuilder;
import cotato.backend.domain.application.dao.ApplicationRepository;
import cotato.backend.domain.application.dto.response.FilterApplicationResponse;
import cotato.backend.domain.application.entity.Application;
import cotato.backend.domain.application.entity.QApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationQueryService {

    private final ApplicationRepository applicationRepository;

    public Page<FilterApplicationResponse> getFilterApplication(String query, String type, Pageable pageable){
        // Q클래스 정의
        QApplication application = QApplication.application;

        //BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        //BooleanBuilder 사용

        // 동적 쿼리: 필터 조건(지원 기수별, 좋아요 높은 순, 지원 기수 + 좋아요 높은 순)
        String sortType = null;
        if(type.equals("period")){
            Integer periodNum = Integer.parseInt(query);
            builder.and(application.period.eq(periodNum));
        }
        else if(type.equals("like")){
            sortType = "like_desc";
        }
        else if(type.equals("both")){
            sortType = "like_desc";
            Integer periodNum = Integer.parseInt(query);
            builder.and(application.period.eq(periodNum));
        }

        Page<Application> applicationPage = applicationRepository.getFilterApplication(builder, sortType, pageable);

        Page<FilterApplicationResponse> dtoList = applicationPage
                .map(FilterApplicationResponse::from);

        return dtoList;
    }
}
