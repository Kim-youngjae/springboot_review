package com.yj.sbbasic1;

import com.yj.sbbasic1.answer.Answer;
import com.yj.sbbasic1.answer.AnswerRepository;
import com.yj.sbbasic1.question.Question;
import com.yj.sbbasic1.question.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class Sbbasic1ApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Test
	@DisplayName("1: 리포지터리 테스트")
	void t001() {

		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);  // 첫번째 질문 저장

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);  // 두번째 질문 저장
	}

	@Test
	@DisplayName("2: DB에 저장된 데이터 조회")
	void t002() {
		List<Question> questionList = this.questionRepository.findAll();

		assertThat(questionList.size()).isEqualTo(2);

		assertThat(questionList.get(0).getSubject()).isEqualTo("sbb가 무엇인가요?");
	}

	@Test
	@DisplayName("3: id값으로 데이터 조회")
	void t003() {
		Optional<Question> oq = this.questionRepository.findById(1);

		if (oq.isPresent()) {
			Question question = oq.get();
			assertThat(question.getSubject()).isEqualTo("sbb가 무엇인가요?");
		}
	}

	@Test
	@DisplayName("4: findBySubject() ")
	void t004() {
		Optional<Question> oq = this.questionRepository.findBySubject("sbb가 무엇인가요?");

		if (oq.isPresent()) {
			Question question = oq.get();

			assertThat(question.getContent()).isEqualTo("sbb에 대해서 알고 싶습니다.");
		}
	}

	@Test
	@DisplayName("5: findBySubjectAndContent()")
	void t005() {
		Optional<Question> oq = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");

		if (oq.isPresent()) {
			Question question = oq.get();

			assertThat(question.getId()).isEqualTo(1);
		}
	}

	@Test
	@DisplayName("6: 데이터 수정하기")
	void t006() {
		Optional<Question> oq = this.questionRepository.findById(1);

		if (oq.isPresent()) {
			Question question = oq.get();

			question.setSubject("수정된 제목");

			this.questionRepository.save(question);

			assertThat(question.getContent()).isEqualTo("sbb에 대해서 알고 싶습니다.");
		}
	}

	@Test
	@DisplayName("7: 데이터 삭제하기")
	void t007() {
		long count = this.questionRepository.count();

		assertThat(count).isEqualTo(2);

		Optional<Question> oq = this.questionRepository.findById(1);

		if (oq.isPresent()) {
			Question question = oq.get();

			this.questionRepository.delete(question);

			assertThat(this.questionRepository.count()).isEqualTo(1);
		}
	}

	@Test
	@DisplayName("8: 답변 데이터 생성하기")
	void t008() {
		Optional<Question> oq = this.questionRepository.findById(2);

		if (oq.isPresent()) {
			Question question = oq.get();

			Answer a = new Answer();

			a.setContent("네 자동으로 생성됩니다.");
			a.setQuestion(question);
			a.setCreateDate(LocalDateTime.now());
			this.answerRepository.save(a);
		}
	}

	@Transactional
	@Test
	@DisplayName("9: 답변에 연결된 질문 찾기")
	void t009() {
		Optional<Question> oq = this.questionRepository.findById(2);

		if (oq.isPresent()) {
			Question q = oq.get();

			List<Answer> answerList = q.getAnswerList();

			assertThat(answerList.size()).isEqualTo(1);

			assertThat(answerList.get(0)).isEqualTo("네 자동으로 생성됩니다.");
		}
	}
}