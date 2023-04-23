package com.yj.sbbasic1.question;

import com.yj.sbbasic1.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getList() { // 가져오는 일을 담당하는 것은 서비스가 하는 일
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);

        if (question.isPresent()) {
            return question.get(); // 객체가 있으면 리턴
        } else {
            throw new DataNotFoundException("question not found"); // 객체가 없으면 예외 발생 -> null을 리턴하므로
        }
    }
}
