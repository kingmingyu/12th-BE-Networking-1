package cotato.backend.domain.application.entity;

import cotato.backend.domain.common.entity.BaseEntity;
import cotato.backend.domain.member.entity.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "application")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Application extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "period", nullable = false)
    private int period;

    @Enumerated(EnumType.STRING)
    @Column(name = "part")
    private Part part;

    @Column(name = "ability", nullable = false)
    @Min(value = 1, message = "점수는 1이상 10이하의 정수입니다.")
    @Max(value = 10, message = "점수는 1이상 10이하의 정수입니다.")
    private int ability;

    @Column(name = "passion", nullable = false)
    @Min(value = 1, message = "점수는 1이상 10이하의 정수입니다.")
    @Max(value = 10, message = "점수는 1이상 10이하의 정수입니다.")
    private int passion;

    @OneToMany(mappedBy = "application", cascade = CascadeType.REMOVE)
    List<Like> likes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
