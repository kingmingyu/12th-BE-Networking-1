package cotato.backend.domain.application.application;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import cotato.backend.domain.application.dao.ApplicationRepository;
import cotato.backend.domain.application.entity.Application;
import cotato.backend.domain.application.entity.QApplication;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationQueryDslImpl implements ApplicationQueryDsl {

    private final EntityManager em;

    // 조회 API
    public Page<Application> getFilterApplication(
            Predicate predicate, String sortType, Pageable pageable
    ){

        //JPA 세팅
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        //Q클래스 선언
        QApplication application = QApplication.application;

        JPAQuery<Application> query = queryFactory
                .selectFrom(application)
                .where(predicate);

        if("like_desc".equals(sortType)){
            query.orderBy(application.likes.size().desc());
        }
        List<Application> content = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(application.count())
                .from(application)
                .where(predicate);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}
