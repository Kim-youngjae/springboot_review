package com.yj.sbbasic1.answer;

import com.yj.sbbasic1.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void create(Question question, String content) {
        Answer answer = new Answer();

        answer.setContent(content);
        answer.setQuestion(question);
        answer.setCreateDate(LocalDateTime.now());

        this.answerRepository.save(answer);
    }
}
