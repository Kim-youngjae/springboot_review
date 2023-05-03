package com.yj.sbbasic1.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Optional<Question> findBySubject(String subject);

    Optional<Question> findBySubjectAndContent(String subject, String content);
}
