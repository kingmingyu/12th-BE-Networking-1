package cotato.backend.domain.member.entity;

import cotato.backend.domain.application.entity.Application;
import cotato.backend.domain.application.entity.Like;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
@Getter
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Size(min = 2, max = 10, message = "이름은 2글자 이상 10글자 이하로 입력해주세요")
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "phone_number", nullable = false)
    @Size(min = 11, max = 11, message = "전화번호 형식에 맞춰 입력해주세요(11자)")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 25)
    private Role role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    List<Application> applications = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    List<Like> likes = new ArrayList<>();

    public void updateMemberInfo(String name, int age, String phoneNumber){
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public void updateRole(Role role){
        this.role = role;
    }
}
