package com.yj.sbbasic1.question;

import com.yj.sbbasic1.answer.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter // TODO : 나중에 @Builder 를 통해 빌드패턴 사용해보기
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Integer id;
    @Column(length = 200)
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;
}
